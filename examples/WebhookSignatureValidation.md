# Validating webhook signatures (HMAC-SHA1 and HMAC-SHA256)

By default Twilio signs webhooks with your Auth Token using HMAC-SHA1. If a
[Webhooks Rule](https://www.twilio.com/docs/usage/webhooks/webhook-rules) matches the webhook URL, Twilio signs
with the Setting's SharedKey and algorithm instead, and adds this header:

```
X-Twilio-Webhooks-Signature-Settings: key=<sharedKeyId>,algorithm=HMAC_SHA256
```

Only the hash changes. The signed string is still the full URL followed by the sorted POST parameters.

## Validate any webhook

Read the header to pick the secret and the algorithm:

```java
import com.twilio.security.RequestValidator;

String signature = request.getHeader(RequestValidator.SIGNATURE_HEADER);
String settings = request.getHeader(RequestValidator.SIGNATURE_SETTINGS_HEADER);  // null if absent

// No header means the Auth Token and HMAC_SHA1
String secret = settings != null ? sharedKeySecret : authToken;
String algorithm = RequestValidator.getSignatureAlgorithm(settings);

RequestValidator validator = new RequestValidator(secret);

// Form-encoded body
boolean valid = validator.validate(url, params, signature, algorithm);

// JSON body (the URL carries bodySHA256)
boolean validJson = validator.validate(url, jsonBody, signature, algorithm);
```

If you use more than one SharedKey, look up the secret by the `key=` value in the header.

## Existing code

The 3-argument `validate(...)` methods still use HMAC-SHA1, so code that validates with the Auth Token needs no change:

```java
new RequestValidator(authToken).validate(url, params, signature);
```

## Where the values come from

The validator needs only plain values, so it works with any framework or server. Read these from your request:

- `url`: the full public URL Twilio calls, including the query string. Behind a proxy or tunnel, use the public `https` URL, not the internal one.
- `params`: every form field of the POST body, including empty ones, as a `Map<String, String>`. For JSON webhooks pass the raw body string instead.
- `signature`: the `X-Twilio-Signature` header (`RequestValidator.SIGNATURE_HEADER`).
- `settings`: the `X-Twilio-Webhooks-Signature-Settings` header (`RequestValidator.SIGNATURE_SETTINGS_HEADER`), or `null` if absent.

Respond `403` when `validate(...)` returns `false`.

## Behaviour to know

- An unsupported algorithm makes `validate(...)` return `false`, so the request is rejected rather than throwing.
- `getSignatureAlgorithm(...)` returns `HMAC_SHA1` for a missing or empty header, or one without `algorithm=`.

## Troubleshooting a failed validation

- **Wrong secret:** requests with the settings header need the SharedKey secret, not the Auth Token.
- **Wrong URL:** behind a proxy or tunnel, your framework may report `http` or an internal host. Use the public URL.
- **Dropped empty parameters:** pass every form field, including empty ones.
- **Parsed body:** for JSON webhooks pass the raw body string, not a re-serialized one.
