package com.twilio.security;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Test class for {@link RequestValidator}.
 */
public class RequestValidatorTest {
    Map<String, String> params = new HashMap<>();
    String url = "https://mycompany.com/myapp.php?foo=1&bar=2";
    RequestValidator validator = new RequestValidator("12345");
    String signature = "RSOYDt4T1cUTdK1PDd93/VVr8B8=";
    String body = "{\"property\": \"value\", \"boolean\": true}";
    String bodySignature = "Ch/3Y02as7ldtcmi3+lBbkFQKyg6gMfPGWMmMvluZiA=";

    @Before
    public void setUp() {
        params.put("CallSid", "CA1234567890ABCDE");
        params.put("Caller", "+14158675309");
        params.put("Digits", "1234");
        params.put("From", "+14158675309");
        params.put("To", "+18005551212");
    }

    @Test
    public void testValidate() {
        Assert.assertTrue("Request does not match provided signature", validator.validate(url, params, signature));
    }

    @Test
    public void testFailsWhenIncorrect() {
        signature = "NOTRSOYDt4T1cUTdK1PDd93/VVr8B8=";
        boolean isValid = validator.validate(url, params, signature);

        Assert.assertFalse("Request should have failed validation, but didn't", isValid);
    }

    @Test
    public void testValidateBody() {
        boolean isValid = validator.validateBody(body, bodySignature);

        Assert.assertTrue("Body validation failed", isValid);
    }

    @Test
    public void testValidateBodyFailsWhenWrong() {
        boolean isValid = validator.validateBody(body, "WRONG");

        Assert.assertFalse("Body validation should have failed, but didn't", isValid);
    }

    @Test
    public void testValidateWithBody() {
        params.put("bodySHA256", bodySignature);
        signature = "lhN9sMASXtkij921mMLP/O8yo04=";

        boolean isValid = validator.validate(url, params, body, signature);

        Assert.assertTrue("Body validation failed", isValid);
    }

    @Test
    public void testValidateBodyWithoutSignature() {
        boolean isValid = validator.validate(url, params, body, signature);

        Assert.assertFalse("Validation should have failed with no bodySHA256", isValid);
    }

}
