package com.twilio.security;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Test class for {@link RequestValidator}.
 */
public class RequestValidatorTest {
    private Map<String, String> params = new HashMap<>();
    private String url = "https://mycompany.com/myapp.php?foo=1&bar=2";
    private RequestValidator validator = new RequestValidator("12345");
    private String signature = "RSOYDt4T1cUTdK1PDd93/VVr8B8=";
    private String body = "{\"property\": \"value\", \"boolean\": true}";
    private String bodyHash = "0a1ff7634d9ab3b95db5c9a2dfe9416e41502b283a80c7cf19632632f96e6620";

    @Before
    public void setUp() {
        params.put("Digits", "1234");
        params.put("CallSid", "CA1234567890ABCDE");
        params.put("To", "+18005551212");
        params.put("Caller", "+14158675309");
        params.put("From", "+14158675309");
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
        boolean isValid = validator.validateBody(body, bodyHash);

        Assert.assertTrue("Body validation failed", isValid);
    }

    @Test
    public void testValidateBodyFailsWhenWrong() {
        boolean isValid = validator.validateBody(body, "WRONG");

        Assert.assertFalse("Body validation should have failed, but didn't", isValid);
    }

    @Test
    public void testValidateWithBody() throws URISyntaxException {
        String url = this.url + "&bodySHA256=" + bodyHash;
        String signatureWithHash = "a9nBmqA0ju/hNViExpshrM61xv4=";
        boolean isValid = validator.validate(url, body, signatureWithHash);

        Assert.assertTrue("Body validation failed", isValid);
    }

    @Test
    public void testValidateWithNoOtherParameters() throws URISyntaxException {
        String url = "https://mycompany.com/myapp.php?bodySHA256=" + bodyHash;
        String signatureWithHash = "y77kIzt2vzLz71DgmJGsen2scGs=";
        boolean isValid = validator.validate(url, body, signatureWithHash);

        Assert.assertTrue("Body validation failed", isValid);
    }

    @Test
    public void testValidateBodyWithoutSignature() throws URISyntaxException {
        boolean isValid = validator.validate(url, body, signature);

        Assert.assertFalse("Validation should have failed with no bodySHA256", isValid);
    }

    @Test
    public void testValidateRemovesPortHttps() {
        String url = this.url.replace(".com", ".com:1234");
        boolean isValid = validator.validate(url, params, signature);

        Assert.assertTrue("Validator did not strip port from url", isValid);
    }

    @Test
    public void testValidateRemovesPortHttp() {
        String url = this.url.replace(".com", ".com:1234").replace("https", "http");
        String expectedSignature = "Zmvh+3yNM1Phv2jhDCwEM3q5ebU="; // hash of http uri with port 1234
        boolean isValid = validator.validate(url, params, expectedSignature);

        Assert.assertTrue("Validator did not strip port from url", isValid);
    }

    @Test
    public void testValidateAddsPortHttps() {
        String expectedSignature = "kvajT1Ptam85bY51eRf/AJRuM3w="; // hash of https uri with port 443
        boolean isValid = validator.validate(url, params, expectedSignature);
        
        Assert.assertTrue("Validator did not add port 443 to https url", isValid);
    }
    
    @Test
    public void testValidateAddsPortHttp() {
        String url = this.url.replace("https", "http");
        String expectedSignature = "0ZXoZLH/DfblKGATFgpif+LLRf4="; // hash of http uri with port 80
        boolean isValid = validator.validate(url, params, expectedSignature);

        Assert.assertTrue("Validator did not add port 80 to http url", isValid);
    }

    @Test
    public void testValidateRemovesPortHttpsWithSpecialParams() {
        String url = "https://mycompany.com/myapp.php";
        String param = "?param1=client%3AAnonymous";
        url = url + param;
        boolean isValid = validator.validate(url, params, "iQIea/lNtwezVhrpQ/KiGTEmJDA=");

        Assert.assertTrue("Validator did not strip port from url", isValid);

        url = url.replace(".com", ".com:1234");
        isValid = validator.validate(url, params, "g7dthJtIvMAeMJ8XhiywDKG63Gg=");

        Assert.assertTrue("Validator did not strip port from url", isValid);
    }

}
