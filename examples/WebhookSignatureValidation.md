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

String signature = request.getHeader("X-Twilio-Signature");
String settings = request.getHeader(RequestValidator.SIGNATURE_SETTINGS_HEADER);

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
