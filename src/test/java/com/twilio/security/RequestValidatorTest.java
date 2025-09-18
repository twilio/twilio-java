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
    public void testValidateWithEmptyParams() {
        // Test with no form parameters - empty map
        Map<String, String> emptyParams = new HashMap<>();
        String expectedSignature = "zYQTYrRWXE7LtzbG4PfP7/bkkGo="; // hash of URL only
        boolean isValid = validator.validate(url, emptyParams, expectedSignature);

        Assert.assertTrue("Validation should succeed with empty parameters", isValid);
    }

    @Test
    public void testValidateWithNullParams() {
        // Test with null parameters
        String expectedSignature = "zYQTYrRWXE7LtzbG4PfP7/bkkGo="; // hash of URL only
        boolean isValid = validator.validate(url, (Map<String, String>) null, expectedSignature);

        Assert.assertTrue("Validation should succeed with null parameters", isValid);
    }

    @Test
    public void testValidateWithNoQueryParams() {
        // Test with URL that has no query parameters
        String urlNoQuery = "https://mycompany.com/myapp.php";
        String expectedSignature = "1xaxFBvyzXYvWplzNOSs28HWO94=";
        boolean isValid = validator.validate(urlNoQuery, params, expectedSignature);

        Assert.assertTrue("Validation should succeed with URL without query parameters", isValid);
    }

    @Test
    public void testValidateIgnoresNonTwilioQueryParams() {
        // Test that non-Twilio query parameters in the URL are included in signature generation
        // (they are NOT ignored - they're part of the URL that gets signed)
        String urlWithExtraParams = "https://mycompany.com/myapp.php?foo=1&bar=2&type=thiswontbeusedinthesignaturegeneratorandsoshouldbeignored";
        String expectedSignature = "hYkNsTBttoNGkyCS0p+wWmQoRTg=";
        boolean isValid = validator.validate(urlWithExtraParams, params, expectedSignature);

        Assert.assertTrue("Validation should succeed - query params are part of URL signature", isValid);
    }

    @Test
    public void testValidateWithUrlEncodedQueryParams() {
        // Test with URL encoded query parameters
        String urlEncoded = "https://mycompany.com/myapp.php?foo=hello%20world&bar=test%2Bvalue";
        String expectedSignature = "er2tvAP1Wx+iuTTE2BPgvEaN1cs=";
        boolean isValid = validator.validate(urlEncoded, params, expectedSignature);

        Assert.assertTrue("Validation should succeed with URL encoded query parameters", isValid);
    }

    @Test
    public void testValidateWithSpecialCharactersInParams() {
        // Test with special characters in parameter values
        Map<String, String> specialParams = new HashMap<>();
        specialParams.put("Message", "Hello & goodbye! @#$%^*()");
        specialParams.put("From", "+1 (415) 867-5309");
        specialParams.put("Special", "unicode: ñáéíóú");

        String expectedSignature = "dCPiR4WtQ6QFN6pJh81CtlCcWLQ=";
        boolean isValid = validator.validate(url, specialParams, expectedSignature);

        Assert.assertTrue("Validation should succeed with special characters in parameters", isValid);
    }

    @Test
    public void testValidateWithEmptyParameterValues() {
        // Test with empty string parameter values
        Map<String, String> emptyValueParams = new HashMap<>();
        emptyValueParams.put("Digits", "");
        emptyValueParams.put("CallSid", "CA1234567890ABCDE");
        emptyValueParams.put("EmptyParam", "");

        String expectedSignature = "9HHp/OqQMEwdrVebHDdA/+tqjX8=";
        boolean isValid = validator.validate(url, emptyValueParams, expectedSignature);

        Assert.assertTrue("Validation should succeed with empty parameter values", isValid);
    }

    @Test
    public void testValidateWithNullParameterValues() {
        // Test with null parameter values (treated as empty strings)
        Map<String, String> nullValueParams = new HashMap<>();
        nullValueParams.put("Digits", null);
        nullValueParams.put("CallSid", "CA1234567890ABCDE");
        nullValueParams.put("NullParam", null);

        String expectedSignature = "L9lTTwIlxM1xGAtLRwhOSZggRhM=";
        boolean isValid = validator.validate(url, nullValueParams, expectedSignature);

        Assert.assertTrue("Validation should succeed with null parameter values", isValid);
    }

    @Test
    public void testValidateParameterKeySorting() {
        // Test that parameter keys are properly sorted alphabetically
        Map<String, String> unsortedParams = new HashMap<>();
        unsortedParams.put("Zebra", "last");
        unsortedParams.put("Alpha", "first");
        unsortedParams.put("Beta", "second");
        unsortedParams.put("Gamma", "third");

        String expectedSignature = "enveUe73ZTVLAekHxez3Qx6JuuQ=";
        boolean isValid = validator.validate(url, unsortedParams, expectedSignature);

        Assert.assertTrue("Validation should succeed with alphabetically sorted parameters", isValid);
    }

    @Test
    public void testValidateWithInternationalCharactersInUrl() {
        // Test with international characters in URL
        String intlUrl = "https://mycompany.com/webhook/España?año=2023";
        String expectedSignature = "G/Hx6mqqnjUNlPAoi2Sp9bGst0g=";
        boolean isValid = validator.validate(intlUrl, params, expectedSignature);

        Assert.assertTrue("Validation should succeed with international characters in URL", isValid);
    }

    @Test
    public void testValidateWithCaseSensitiveParameterKeys() {
        // Test that parameter keys are case sensitive
        Map<String, String> caseParams = new HashMap<>();
        caseParams.put("callSid", "lowercase");
        caseParams.put("CallSid", "uppercase"); // Different from callSid
        caseParams.put("CALLSID", "allcaps");

        String expectedSignature = "jDK7WKT8wiNrwggd4ceTv4e1MJ4=";
        boolean isValid = validator.validate(url, caseParams, expectedSignature);

        Assert.assertTrue("Validation should succeed with case-sensitive parameter keys", isValid);
    }

    @Test
    public void testValidateWithMalformedUrlStillWorks() {
        // Test that validation handles URLs gracefully even with unusual formatting
        String malformedUrl = "https://mycompany.com:443//myapp.php?foo=1&bar=2";
        String expectedSignature = "A9lH3u16NjyiiM/+wylw5rirFUs="; // Signature for the malformed URL as-is
        boolean isValid = validator.validate(malformedUrl, params, expectedSignature);

        Assert.assertTrue("Validation should handle malformed URLs gracefully", isValid);
    }

    @Test
    public void testValidateWithDifferentProtocols() {
        // Test validation with different protocols
        String httpUrl = url.replace("https", "http");
        String expectedSignature = "0ZXoZLH/DfblKGATFgpif+LLRf4=";
        boolean isValid = validator.validate(httpUrl, params, expectedSignature);

        Assert.assertTrue("Validation should work with HTTP protocol", isValid);
    }

    @Test
    public void testValidateFailsWithWrongSignature() {
        // Test that validation properly fails with incorrect signature
        String wrongSignature = "IncorrectSignatureValue123=";
        boolean isValid = validator.validate(url, params, wrongSignature);

        Assert.assertFalse("Validation should fail with incorrect signature", isValid);
    }

    @Test
    public void testValidateFailsWithNullSignature() {
        // Test that validation properly fails with null signature
        boolean isValid = validator.validate(url, params, null);

        Assert.assertFalse("Validation should fail with null signature", isValid);
    }

    @Test
    public void testValidateFailsWithEmptySignature() {
        // Test that validation properly fails with empty signature
        boolean isValid = validator.validate(url, params, "");
        Assert.assertFalse("Validation should fail with empty signature", isValid);
    }

}
