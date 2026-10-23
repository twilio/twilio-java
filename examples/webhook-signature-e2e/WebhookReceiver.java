import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import com.twilio.security.RequestValidator;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Minimal webhook receiver for the end-to-end signature test (see README.md).
 *
 * Validates X-Twilio-Signature with the RequestValidator from this checkout and prints one RESULT line
 * per request. Requests signed with a SharedKey carry X-Twilio-Webhooks-Signature-Settings
 * (key=...,algorithm=...); without that header Twilio signed with the account Auth Token (HMAC-SHA1).
 *
 * Environment: PORT, PUBLIC_URL (the URL Twilio calls, no trailing slash), SHARED_KEY_SECRET, TWILIO_AUTH_TOKEN.
 */
public class WebhookReceiver {

    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(env("PORT", "5055"));
        String publicUrl = stripSlash(env("PUBLIC_URL", "http://localhost:" + port));
        String sharedKeySecret = env("SHARED_KEY_SECRET", "");
        String authToken = env("TWILIO_AUTH_TOKEN", "");

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", exchange -> {
            try {
                handle(exchange, publicUrl, sharedKeySecret, authToken);
            } catch (Exception e) {
                System.out.println("ERROR " + exchange.getRequestURI() + " " + e);
                respond(exchange, 500, "text/plain", "error");
            }
        });
        server.start();
        System.out.println("listening on :" + port + " public=" + publicUrl);
    }

    private static void handle(HttpExchange exchange, String publicUrl, String sharedKeySecret, String authToken)
        throws Exception {
        String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        String signature = orEmpty(exchange.getRequestHeaders().getFirst("X-Twilio-Signature"));
        String settings = exchange.getRequestHeaders().getFirst("X-Twilio-Webhooks-Signature-Settings");
        String contentType = orEmpty(exchange.getRequestHeaders().getFirst("Content-Type"));
        String algorithm = algorithmFrom(settings);
        String secret = settings != null ? sharedKeySecret : authToken;

        // Validate against the URL Twilio called (public URL + path + query), not localhost.
        String url = publicUrl + exchange.getRequestURI();
        RequestValidator validator = new RequestValidator(secret);

        boolean valid;
        boolean legacySha1Valid;
        if (contentType.startsWith("application/json")) {
            valid = validator.validate(url, body, signature, algorithm);
            legacySha1Valid = validator.validate(url, body, signature);
        } else {
            Map<String, String> params = formParams(body);
            valid = validator.validate(url, params, signature, algorithm);
            legacySha1Valid = validator.validate(url, params, signature);
        }

        System.out.println("RESULT path=" + exchange.getRequestURI().getPath()
            + " method=" + exchange.getRequestMethod()
            + " contentType=" + (contentType.isEmpty() ? "-" : contentType.split(";")[0])
            + " bodySHA256InUrl=" + url.contains("bodySHA256=")
            + " signed=" + (settings != null ? "SHARED_KEY" : "AUTH_TOKEN")
            + " algorithm=" + (algorithm == null ? "HMAC_SHA1" : algorithm)
            + " valid=" + valid
            + " legacySha1Valid=" + legacySha1Valid);

        // Voice answer URLs need TwiML back, or Twilio fails the call.
        if (exchange.getRequestURI().getPath().startsWith("/hooks/voice")) {
            respond(exchange, 200, "text/xml",
                "<Response><Say>Twilio SDK signature test.</Say><Hangup/></Response>");
        } else {
            respond(exchange, 200, "application/json", "{\"valid\":" + valid + "}");
        }
    }

    /** Parses "key=<id>,algorithm=<alg>"; a missing or empty header (or no algorithm) means HMAC-SHA1. */
    private static String algorithmFrom(String settingsHeader) {
        if (settingsHeader == null || settingsHeader.isBlank()) {
            return null;
        }
        for (String part : settingsHeader.split(",")) {
            String p = part.trim();
            if (p.startsWith("algorithm=")) {
                return p.substring("algorithm=".length());
            }
        }
        return null;
    }

    private static Map<String, String> formParams(String body) {
        Map<String, String> params = new HashMap<>();
        if (body.isEmpty()) {
            return params;
        }
        for (String kv : body.split("&")) {
            String[] p = kv.split("=", 2);
            params.put(URLDecoder.decode(p[0], StandardCharsets.UTF_8),
                p.length > 1 ? URLDecoder.decode(p[1], StandardCharsets.UTF_8) : "");
        }
        return params;
    }

    private static void respond(HttpExchange exchange, int status, String contentType, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.sendResponseHeaders(status, bytes.length);
        try (OutputStream out = exchange.getResponseBody()) {
            out.write(bytes);
        }
    }

    private static String env(String name, String fallback) {
        String value = System.getenv(name);
        return value == null || value.isEmpty() ? fallback : value.trim();
    }

    private static String orEmpty(String s) {
        return s == null ? "" : s;
    }

    private static String stripSlash(String s) {
        return s.endsWith("/") ? s.substring(0, s.length() - 1) : s;
    }
}
