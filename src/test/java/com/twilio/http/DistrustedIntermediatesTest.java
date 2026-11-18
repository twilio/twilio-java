package com.twilio.http;

import com.twilio.exception.ApiException;
import io.jsonwebtoken.Jwts;
import java.net.InetAddress;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Set;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.tls.HandshakeCertificates;
import okhttp3.tls.HeldCertificate;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DistrustedIntermediatesTest {

    // An IP literal rather than "localhost": when a handshake fails, Apache HttpClient moves on to the host's next
    // address and reports that attempt's error, which would hide the distrust failure behind "connection refused".
    private static final String HOST = "127.0.0.1";

    private static HeldCertificate root;
    private static HeldCertificate intermediate;
    private static HeldCertificate leaf;
    private static X509TrustManager trustsRoot;

    private MockWebServer server;

    @BeforeClass
    public static void createChain() {
        root = new HeldCertificate.Builder()
            .commonName("Test Root")
            .certificateAuthority(1)
            .build();
        intermediate = new HeldCertificate.Builder()
            .commonName("Test Intermediate")
            .certificateAuthority(0)
            .signedBy(root)
            .build();
        leaf = new HeldCertificate.Builder()
            .commonName(HOST)
            .addSubjectAlternativeName(HOST)
            .signedBy(intermediate)
            .build();
        trustsRoot = new HandshakeCertificates.Builder()
            .addTrustedCertificate(root.certificate())
            .build()
            .trustManager();
    }

    @Before
    public void startServer() throws Exception {
        final HandshakeCertificates serverCertificates = new HandshakeCertificates.Builder()
            .heldCertificate(leaf, intermediate.certificate())
            .build();
        server = new MockWebServer();
        server.useHttps(serverCertificates.sslSocketFactory(), false);
        server.start(InetAddress.getByName(HOST), 0);
    }

    @After
    public void stopServer() throws Exception {
        server.shutdown();
    }

    @Test
    public void testBlocklistHasEightLowercaseSha256Hashes() {
        assertEquals(8, DistrustedIntermediates.DISTRUSTED_SPKI_SHA256.size());
        for (final String hash : DistrustedIntermediates.DISTRUSTED_SPKI_SHA256) {
            assertTrue(hash, hash.matches("[0-9a-f]{64}"));
        }
    }

    @Test
    public void testSubjectPublicKeyInfoMatchesEncodedKey() {
        final X509Certificate ecdsa = leaf.certificate();
        final X509Certificate rsa = new HeldCertificate.Builder().rsa2048().build().certificate();
        assertArrayEquals(ecdsa.getPublicKey().getEncoded(), DistrustedIntermediates.subjectPublicKeyInfo(ecdsa));
        assertArrayEquals(rsa.getPublicKey().getEncoded(), DistrustedIntermediates.subjectPublicKeyInfo(rsa));
    }

    @Test
    public void testContainsDistrusted() {
        final X509Certificate[] chain = {leaf.certificate(), intermediate.certificate()};
        assertFalse(DistrustedIntermediates.containsDistrusted(chain, Collections.<String>emptySet()));
        assertFalse(DistrustedIntermediates.containsDistrusted(chain, DistrustedIntermediates.DISTRUSTED_SPKI_SHA256));
        assertTrue(DistrustedIntermediates.containsDistrusted(chain, distrust(intermediate)));
        assertTrue(DistrustedIntermediates.containsDistrusted(chain, distrust(leaf)));
    }

    @Test
    public void testDefaultTrustManagerIsExtended() {
        assertTrue(DistrustedIntermediates.createTrustManager() instanceof X509ExtendedTrustManager);
    }

    @Test
    public void testCleanChainIsAccepted() {
        server.enqueue(new MockResponse().setBody("ok"));
        final NetworkHttpClient client = networkClient(trustsRoot, Collections.<String>emptySet());
        assertEquals(200, client.makeRequest(get()).getStatusCode());
    }

    @Test
    public void testDistrustedIntermediateIsRejected() {
        assertRejected(networkClient(trustsRoot, distrust(intermediate)));
    }

    @Test
    public void testDistrustedLeafIsRejected() {
        assertRejected(networkClient(trustsRoot, distrust(leaf)));
    }

    @Test
    public void testPlainDelegateCleanChainIsAccepted() {
        server.enqueue(new MockResponse().setBody("ok"));
        final NetworkHttpClient client = networkClient(plain(trustsRoot), Collections.<String>emptySet());
        assertEquals(200, client.makeRequest(get()).getStatusCode());
    }

    @Test
    public void testPlainDelegateDistrustedIntermediateIsRejected() {
        assertRejected(networkClient(plain(trustsRoot), distrust(intermediate)));
    }

    @Test
    public void testPre54ConnectionManagerAcceptsCleanChain() {
        server.enqueue(new MockResponse().setBody("ok"));
        assertEquals(200, customClient(Collections.<String>emptySet(), true).makeRequest(get()).getStatusCode());
    }

    @Test
    public void testPre54ConnectionManagerRejectsDistrustedIntermediate() {
        assertRejected(customClient(distrust(intermediate), true));
    }

    @Test
    public void testCustomClientConnectionManagerRejectsDistrustedIntermediate() {
        assertRejected(customClient(distrust(intermediate), false));
    }

    @Test
    public void testValidationClientRejectsDistrustedIntermediate() throws Exception {
        final PrivateKey privateKey = KeyPairGenerator.getInstance("RSA").generateKeyPair().getPrivate();
        final ValidationClient client = new ValidationClient(
            "dummy-sid1", "dummy-sid2", "dummy-signing-key", privateKey,
            HttpClient.DEFAULT_REQUEST_CONFIG, HttpClient.DEFAULT_SOCKET_CONFIG, Jwts.SIG.RS256,
            DistrustedIntermediates.wrap(trustsRoot, distrust(intermediate))
        );
        assertRejected(client);
    }

    @Test
    public void testHostnameMismatchIsRejected() throws Exception {
        assertHostnameMismatchRejected(networkClient(trustsRoot, Collections.<String>emptySet()));
    }

    @Test
    public void testPre54ConnectionManagerRejectsHostnameMismatch() throws Exception {
        assertHostnameMismatchRejected(customClient(Collections.<String>emptySet(), true));
    }

    @Test
    public void testDefaultClientStillRejectsUnknownRoot() {
        try {
            new NetworkHttpClient().makeRequest(get());
            fail("expected the JVM trust store to reject the test root");
        } catch (final ApiException e) {
            assertTrue("unexpected failure: " + e, e.getCause() instanceof SSLHandshakeException);
            assertFalse(causedByDistrust(e));
        }
        assertEquals(0, server.getRequestCount());
    }

    private void assertRejected(final HttpClient client) {
        try {
            client.makeRequest(get());
            fail("expected the handshake to fail");
        } catch (final ApiException e) {
            assertTrue("unexpected failure: " + e, causedByDistrust(e));
        }
        // The request, including its Authorization header, never reached the server.
        assertEquals(0, server.getRequestCount());
    }

    // The chain is trusted and nothing in it is distrusted; only the hostname is wrong, so a rejection shows that
    // hostname verification survived the connection manager rebuild.
    private void assertHostnameMismatchRejected(final HttpClient client) throws Exception {
        final HeldCertificate otherHost = new HeldCertificate.Builder()
            .commonName("example.com")
            .addSubjectAlternativeName("example.com")
            .signedBy(intermediate)
            .build();
        final MockWebServer mismatched = new MockWebServer();
        mismatched.useHttps(new HandshakeCertificates.Builder()
            .heldCertificate(otherHost, intermediate.certificate())
            .build()
            .sslSocketFactory(), false);
        mismatched.start(InetAddress.getByName(HOST), 0);
        try {
            client.makeRequest(new Request(HttpMethod.GET, "https://" + HOST + ":" + mismatched.getPort() + "/"));
            fail("expected hostname verification to fail");
        } catch (final ApiException e) {
            assertTrue("unexpected failure: " + e, causedBy(e, SSLException.class));
            assertFalse(causedByDistrust(e));
        } finally {
            mismatched.shutdown();
        }
        assertEquals(0, mismatched.getRequestCount());
    }

    private static boolean causedBy(final Throwable e, final Class<? extends Throwable> type) {
        for (Throwable t = e; t != null; t = t.getCause()) {
            if (type.isInstance(t)) {
                return true;
            }
        }
        return false;
    }

    private static boolean causedByDistrust(final Throwable e) {
        for (Throwable t = e; t != null; t = t.getCause()) {
            if (t.getMessage() != null && t.getMessage().contains("distrusted intermediate")) {
                return true;
            }
        }
        return false;
    }

    private Request get() {
        return new Request(HttpMethod.GET, "https://" + HOST + ":" + server.getPort() + "/");
    }

    private static NetworkHttpClient networkClient(final X509TrustManager delegate, final Set<String> distrusted) {
        return new NetworkHttpClient(
            HttpClient.DEFAULT_REQUEST_CONFIG,
            HttpClient.DEFAULT_SOCKET_CONFIG,
            DistrustedIntermediates.wrap(delegate, distrusted)
        );
    }

    // Builds the client the way an application would with createConnectionManager(), using the connection manager
    // for httpclient5 before 5.4 when legacy is true.
    private static NetworkHttpClient customClient(final Set<String> distrusted, final boolean legacy) {
        final SSLContext sslContext = DistrustedIntermediates.createSslContext(
            DistrustedIntermediates.wrap(trustsRoot, distrusted));
        final PoolingHttpClientConnectionManager connectionManager = legacy
            ? DistrustedIntermediates.SocketFactoryConnectionManager.create(sslContext)
            : DistrustedIntermediates.TlsStrategyConnectionManager.create(sslContext);
        return new NetworkHttpClient(HttpClientBuilder.create().setConnectionManager(connectionManager));
    }

    private static Set<String> distrust(final HeldCertificate certificate) {
        return Collections.singleton(DistrustedIntermediates.spkiSha256Hex(certificate.certificate()));
    }

    // Hides the X509ExtendedTrustManager methods so wrap() takes its plain-delegate path.
    private static X509TrustManager plain(final X509TrustManager delegate) {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {
                delegate.checkClientTrusted(chain, authType);
            }

            @Override
            public void checkServerTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {
                delegate.checkServerTrusted(chain, authType);
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return delegate.getAcceptedIssuers();
            }
        };
    }
}
