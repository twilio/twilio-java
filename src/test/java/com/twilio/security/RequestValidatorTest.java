package com.twilio.security;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Test class for {@link RequestValidator}.
 */
public class RequestValidatorTest {

    @Test
    public void testValidate() {

        RequestValidator validator = new RequestValidator("12345");

        String url = "https://mycompany.com/myapp.php?foo=1&bar=2";
        Map<String, String> params = new HashMap<>();
        params.put("CallSid", "CA1234567890ABCDE");
        params.put("Caller", "+14158675309");
        params.put("Digits", "1234");
        params.put("From", "+14158675309");
        params.put("To", "+18005551212");

        String signature = "RSOYDt4T1cUTdK1PDd93/VVr8B8=";

        Assert.assertTrue("Request does not match provided signature", validator.validate(url, params, signature));
    }

}
