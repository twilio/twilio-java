package com.twilio.http;

import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.DefaultClientTlsStrategy;
import org.apache.hc.client5.http.ssl.HostnameVerificationPolicy;
import org.apache.hc.client5.http.ssl.HttpsSupport;
import org.apache.hc.core5.ssl.SSLInitializationException;

/**
 * Refuses TLS connections whose certificate chain contains one of eight intermediate certificates that are no
 * longer trustworthy.
 *
 * <p>The private keys of these intermediates are weak, so anyone can use them to issue a certificate that a
 * default trust store accepts. This is the same list Go distrusts as of Go 1.27.3 and 1.26.10
 * (CVE-2026-111943, golang/go#82345). Each certificate is identified by the SHA-256 hash of its DER-encoded
 * SubjectPublicKeyInfo.
 *
 * <p>The SDK's default clients, {@link NetworkHttpClient} and {@link ValidationClient}, apply this check. A
 * {@link NetworkHttpClient} built from your own {@code HttpClientBuilder} doesn't; to opt in, use
 * {@link #createConnectionManager()} as its connection manager:
 *
 * <pre>{@code
 * PoolingHttpClientConnectionManager connectionManager = DistrustedIntermediates.createConnectionManager();
 * HttpClient httpClient = new NetworkHttpClient(HttpClientBuilder.create().setConnectionManager(connectionManager));
 * }</pre>
 *
 * <p>Java's trust manager API exposes the chain the server sent rather than the path the JDK built from it, so
 * the check applies to the sent chain. That is slightly stricter than Go, which accepts a connection if any
 * verified chain avoids these certificates.
 */
public final class DistrustedIntermediates {

    // Copied from Go commit 7f86f957633ee31a1c17a1eb8452ca7a3d9bc141 (src/crypto/x509/verify.go).
    static final Set<String> DISTRUSTED_SPKI_SHA256 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
        "ebed6475c2c2d80fcb199f3fe8dd6e6b3ce60b77eb15d4e42c3dec0c6f5b5105",
        "49dd353dc2b919a3770645117d0e60c16115e7d1bc259eefe734b00ea434fe5f",
        "e0254c7fcdc265e2f0f8a428dabb75ab4ae039b4872d62f5ee57f0877d5a8a41",
        "5c0660a2b164910d8ff589c18e14552e9e0ec237dcefe2cb736d41a4811f81a0",
        "43bffa19d6ef651451f6841e019931e96f6f2eaeff28ab4b6f1ce4576de2717b",
        "63ecefee431d49523bc1136c4e72d0206a477086caf606cd5cf025686c109cf8",
        "ec4ffa1ad0ac88081314b7aaffef6a91f97cd57291bb5ba686c5415f5eb4f6b6",
        "92c063d67e8f14db71d288e11266ffdaf69cbf115f7bab14f3bc2f68d25e0e30"
    )));

    private static final byte[] HEX_DIGITS = "0123456789abcdef".getBytes(StandardCharsets.US_ASCII);

    // TlsSocketStrategy arrived in httpclient5 5.4. Applications can pin an older httpclient5, so only touch it
    // when it's on the classpath.
    private static final boolean HAS_TLS_SOCKET_STRATEGY = isPresent("org.apache.hc.client5.http.ssl.TlsSocketStrategy");

    private DistrustedIntermediates() {
    }

    /**
     * Create an Apache HttpClient connection manager with the same TLS settings as
     * {@code new PoolingHttpClientConnectionManager()}, plus the distrust check.
     *
     * @return a connection manager that refuses distrusted intermediates
     */
    public static PoolingHttpClientConnectionManager createConnectionManager() {
        return createConnectionManager(createTrustManager());
    }

    /**
     * Create a trust manager that delegates to the JVM's default trust manager, then refuses chains that contain a
     * distrusted intermediate.
     *
     * @return an X509 trust manager
     */
    static X509TrustManager createTrustManager() {
        return wrap(defaultTrustManager(), DISTRUSTED_SPKI_SHA256);
    }

    static PoolingHttpClientConnectionManager createConnectionManager(final X509TrustManager trustManager) {
        final SSLContext sslContext = createSslContext(trustManager);
        if (HAS_TLS_SOCKET_STRATEGY) {
            return TlsStrategyConnectionManager.create(sslContext);
        }
        return SocketFactoryConnectionManager.create(sslContext);
    }

    private static boolean isPresent(final String className) {
        try {
            Class.forName(className, false, DistrustedIntermediates.class.getClassLoader());
            return true;
        } catch (final ClassNotFoundException | LinkageError e) {
            return false;
        }
    }

    // Kept in its own class so the httpclient5 5.4+ types it uses are only loaded when they exist.
    static final class TlsStrategyConnectionManager {
        private TlsStrategyConnectionManager() {
        }

        static PoolingHttpClientConnectionManager create(final SSLContext sslContext) {
            // Matches DefaultClientTlsStrategy.createDefault(), which new PoolingHttpClientConnectionManager() uses
            // on 5.4+.
            return PoolingHttpClientConnectionManagerBuilder.create()
                .setTlsSocketStrategy(new DefaultClientTlsStrategy(
                    sslContext,
                    HostnameVerificationPolicy.BOTH,
                    HttpsSupport.getDefaultHostnameVerifier()
                ))
                .build();
        }
    }

    static final class SocketFactoryConnectionManager {
        private SocketFactoryConnectionManager() {
        }

        // Matches SSLConnectionSocketFactory.getSocketFactory(), which new PoolingHttpClientConnectionManager() uses
        // before 5.4. The API is deprecated from 5.4 on, where this class isn't used.
        @SuppressWarnings("deprecation")
        static PoolingHttpClientConnectionManager create(final SSLContext sslContext) {
            return PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(new org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory(
                    sslContext, HttpsSupport.getDefaultHostnameVerifier()))
                .build();
        }
    }

    static SSLContext createSslContext(final X509TrustManager trustManager) {
        try {
            // Matches SSLContexts.createDefault() apart from the trust manager.
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[] {trustManager}, null);
            return sslContext;
        } catch (final GeneralSecurityException e) {
            throw new SSLInitializationException(e.getMessage(), e);
        }
    }

    static X509TrustManager defaultTrustManager() {
        try {
            final TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            factory.init((KeyStore) null);
            for (final TrustManager trustManager : factory.getTrustManagers()) {
                if (trustManager instanceof X509TrustManager) {
                    return (X509TrustManager) trustManager;
                }
            }
        } catch (final GeneralSecurityException e) {
            throw new SSLInitializationException(e.getMessage(), e);
        }
        throw new SSLInitializationException("No X509TrustManager in the default TrustManagerFactory", null);
    }

    static X509TrustManager wrap(final X509TrustManager delegate, final Set<String> distrusted) {
        if (delegate instanceof X509ExtendedTrustManager) {
            return new ExtendedTrustManager((X509ExtendedTrustManager) delegate, distrusted);
        }
        // Stay a plain X509TrustManager so JSSE adds the same endpoint and algorithm checks it would have added
        // around the delegate.
        return new PlainTrustManager(delegate, distrusted);
    }

    static void checkChain(final X509Certificate[] chain, final Set<String> distrusted) throws CertificateException {
        if (containsDistrusted(chain, distrusted)) {
            throw new CertificateException("Certificate chain contains a distrusted intermediate certificate");
        }
    }

    static boolean containsDistrusted(final X509Certificate[] chain, final Set<String> distrusted) {
        for (final X509Certificate certificate : chain) {
            if (distrusted.contains(spkiSha256Hex(certificate))) {
                return true;
            }
        }
        return false;
    }

    static String spkiSha256Hex(final X509Certificate certificate) {
        final byte[] digest;
        try {
            digest = MessageDigest.getInstance("SHA-256").digest(subjectPublicKeyInfo(certificate));
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
        final byte[] hex = new byte[digest.length * 2];
        for (int i = 0; i < digest.length; i++) {
            hex[i * 2] = HEX_DIGITS[(digest[i] >> 4) & 0xf];
            hex[i * 2 + 1] = HEX_DIGITS[digest[i] & 0xf];
        }
        return new String(hex, StandardCharsets.US_ASCII);
    }

    // Go hashes the SubjectPublicKeyInfo bytes exactly as they appear in the certificate. PublicKey.getEncoded()
    // re-encodes the key, which isn't guaranteed to reproduce those bytes, so read them from the TBSCertificate.
    static byte[] subjectPublicKeyInfo(final X509Certificate certificate) {
        try {
            return subjectPublicKeyInfo(certificate.getTBSCertificate());
        } catch (final CertificateEncodingException | IllegalArgumentException e) {
            // The JDK has already parsed this certificate, so this shouldn't happen. The re-encoded key matches the
            // raw bytes for any DER-conformant certificate.
            return certificate.getPublicKey().getEncoded();
        }
    }

    // TBSCertificate ::= SEQUENCE { version [0] EXPLICIT OPTIONAL, serialNumber, signature, issuer, validity,
    //                               subject, subjectPublicKeyInfo, ... }
    static byte[] subjectPublicKeyInfo(final byte[] tbs) {
        final int[] sequence = readHeader(tbs, 0, 0x30);
        int offset = sequence[0];
        if (offset < tbs.length && (tbs[offset] & 0xff) == 0xa0) {
            offset = skip(tbs, offset);
        }
        for (int i = 0; i < 5; i++) {
            offset = skip(tbs, offset);
        }
        final int[] spki = readHeader(tbs, offset, 0x30);
        return Arrays.copyOfRange(tbs, offset, spki[0] + spki[1]);
    }

    private static int skip(final byte[] der, final int offset) {
        final int[] header = readHeader(der, offset, -1);
        return header[0] + header[1];
    }

    // Returns {contentOffset, contentLength} for the DER element at offset. expectedTag is -1 to accept any tag.
    private static int[] readHeader(final byte[] der, final int offset, final int expectedTag) {
        if (offset + 2 > der.length) {
            throw new IllegalArgumentException("Truncated DER element");
        }
        final int tag = der[offset] & 0xff;
        if ((tag & 0x1f) == 0x1f || (expectedTag != -1 && tag != expectedTag)) {
            throw new IllegalArgumentException("Unexpected DER tag " + tag);
        }
        int length = der[offset + 1] & 0xff;
        int contentOffset = offset + 2;
        if (length >= 0x80) {
            final int lengthBytes = length & 0x7f;
            if (lengthBytes == 0 || lengthBytes > 3 || contentOffset + lengthBytes > der.length) {
                throw new IllegalArgumentException("Unsupported DER length");
            }
            length = 0;
            for (int i = 0; i < lengthBytes; i++) {
                length = (length << 8) | (der[contentOffset++] & 0xff);
            }
        }
        if (contentOffset + length > der.length) {
            throw new IllegalArgumentException("Truncated DER element");
        }
        return new int[] {contentOffset, length};
    }

    private static final class ExtendedTrustManager extends X509ExtendedTrustManager {
        private final X509ExtendedTrustManager delegate;
        private final Set<String> distrusted;

        ExtendedTrustManager(final X509ExtendedTrustManager delegate, final Set<String> distrusted) {
            this.delegate = delegate;
            this.distrusted = distrusted;
        }

        @Override
        public void checkServerTrusted(final X509Certificate[] chain, final String authType)
            throws CertificateException {
            delegate.checkServerTrusted(chain, authType);
            checkChain(chain, distrusted);
        }

        @Override
        public void checkServerTrusted(final X509Certificate[] chain, final String authType, final Socket socket)
            throws CertificateException {
            delegate.checkServerTrusted(chain, authType, socket);
            checkChain(chain, distrusted);
        }

        @Override
        public void checkServerTrusted(final X509Certificate[] chain, final String authType, final SSLEngine engine)
            throws CertificateException {
            delegate.checkServerTrusted(chain, authType, engine);
            checkChain(chain, distrusted);
        }

        @Override
        public void checkClientTrusted(final X509Certificate[] chain, final String authType)
            throws CertificateException {
            delegate.checkClientTrusted(chain, authType);
        }

        @Override
        public void checkClientTrusted(final X509Certificate[] chain, final String authType, final Socket socket)
            throws CertificateException {
            delegate.checkClientTrusted(chain, authType, socket);
        }

        @Override
        public void checkClientTrusted(final X509Certificate[] chain, final String authType, final SSLEngine engine)
            throws CertificateException {
            delegate.checkClientTrusted(chain, authType, engine);
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return delegate.getAcceptedIssuers();
        }
    }

    private static final class PlainTrustManager implements X509TrustManager {
        private final X509TrustManager delegate;
        private final Set<String> distrusted;

        PlainTrustManager(final X509TrustManager delegate, final Set<String> distrusted) {
            this.delegate = delegate;
            this.distrusted = distrusted;
        }

        @Override
        public void checkServerTrusted(final X509Certificate[] chain, final String authType)
            throws CertificateException {
            delegate.checkServerTrusted(chain, authType);
            checkChain(chain, distrusted);
        }

        @Override
        public void checkClientTrusted(final X509Certificate[] chain, final String authType)
            throws CertificateException {
            delegate.checkClientTrusted(chain, authType);
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return delegate.getAcceptedIssuers();
        }
    }
}
