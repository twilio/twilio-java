package com.twilio.jwt.validation;

import com.twilio.exception.InvalidRequestException;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class RequestCanonicalizerTest {

    private static final List<String> signedHeaders = Arrays.asList("authorization", "duplicate", "host");

    private static final int PATH_LINE = 1;
    private static final int QUERY_LINE = 2;

    private Header[] headers;

    @Before
    public void setup() {
        headers = new Header[4];
        headers[0] = new BasicHeader("host", "api.twilio.com");
        headers[1] = new BasicHeader("authorization", "foobar");
        headers[2] = new BasicHeader("duplicate", "value2");
        headers[3] = new BasicHeader("Duplicate", "value1");
    }

    @Test
    public void testCreateCanonicalRequest() {
        String queryParams = "PageSize=5&Limit=10";

        String canonicalRequest = canonicalizeWithQueryParams(queryParams);

        Assert.assertEquals("GET\n" + // action
                            "/Messages\n" + // path
                            "Limit=10&PageSize=5\n" + //queryParams
                            "authorization:foobar\n" + // included header #1
                            "duplicate:value1,value2\n" + // included header #2
                            "host:api.twilio.com\n" + // included headar #3
                            "\n" + // empty line after headers
                            "authorization;duplicate;host\n" + // included headers
                            "c3ab8ff13720e8ad9047dd39466b3c8974e592c2fa383d4a3960714caef0c4f2", // body hash
                            canonicalRequest);
    }

    @Test
    public void testCreateCanonicalRequestWithHostPort() {
        String queryParams = "PageSize=5&Limit=10";
        headers[0] = new BasicHeader("host", "api.twilio.com:443");

        String canonicalRequest = canonicalizeWithQueryParams(queryParams);

        Assert.assertEquals("GET\n" + // action
                            "/Messages\n" + // path
                            "Limit=10&PageSize=5\n" + //queryParams
                            "authorization:foobar\n" + // included header #1
                            "duplicate:value1,value2\n" + // included header #2
                            "host:api.twilio.com\n" + // included headar #3
                            "\n" + // empty line after headers
                            "authorization;duplicate;host\n" + // included headers
                            "c3ab8ff13720e8ad9047dd39466b3c8974e592c2fa383d4a3960714caef0c4f2", // body hash
                            canonicalRequest);
    }

    @Test
    public void testReplacesEncodedWhitespaceInQueryParams() {
        String queryParams = "key+with+whitespace=value+with+whitespace";

        String canonicalRequest = canonicalizeWithQueryParams(queryParams);

        Assert.assertEquals("key%20with%20whitespace=value%20with%20whitespace",
                            getLine(QUERY_LINE, canonicalRequest));
    }

    @Test
    public void testDecodesEncodedTildeInQueryParams() {
        String queryParams = "a%7Ea=b%7Eb";

        String canonicalRequest = canonicalizeWithQueryParams(queryParams);

        Assert.assertEquals("a~a=b~b", getLine(QUERY_LINE, canonicalRequest));
    }

    @Test
    public void testEncodesMultiplyInQueryParams() {
        String queryParams = "a*a=b*b";

        String canonicalRequest = canonicalizeWithQueryParams(queryParams);

        Assert.assertEquals("a%2Aa=b%2Ab", getLine(QUERY_LINE, canonicalRequest));
    }

    @Test
    public void testDoesNotEncodeUriEncodedSpace() {
        String queryParams = "a%20a=b%20b";

        String canonicalRequest = canonicalizeWithQueryParams(queryParams);

        Assert.assertEquals("a%20a=b%20b", getLine(QUERY_LINE, canonicalRequest));
    }

    @Test
    public void testDoesNotEncodeEncodedMultiplyInQueryParams() {
        String queryParams = "a%2Aa=b%2Ab";

        String canonicalRequest = canonicalizeWithQueryParams(queryParams);

        Assert.assertEquals("a%2Aa=b%2Ab", getLine(QUERY_LINE, canonicalRequest));
    }

    @Test
    public void testEmptyQuery() {
        String queryParams = "";

        String canonicalRequest = canonicalizeWithQueryParams(queryParams);

        Assert.assertEquals("", getLine(QUERY_LINE, canonicalRequest));
    }

    @Test
    public void testScriptUsedAsQueryMatchesWithServer() {
        String queryParams = "a=%3Csvg+xmlns%3D%22http%3A%2F%2Fwww" +
                             ".w3.org%2F2000%2Fsvg%22+viewBox%3D%220+0+100+100%22%3E%3Cpath+d%3D%22M10%2C10+H90+L50" +
                             "%2C70%22%2F%3E%3Ctext+y%3D%2290%22%3E%22+%27+%23+%25+%26amp%3B+%C2%BF+%F0%9F%94%A3%3C" +
                             "%2Ftext%3E%3C%2Fsvg%3E%0A";

        String canonicalRequest = canonicalizeWithQueryParams(queryParams);

        Assert.assertEquals("a=%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww" +
                            ".w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20100%20100%22%3E%3Cpath%20d%3D%22M10%2C10" +
                            "%20H90%20L50%2C70%22%2F%3E%3Ctext%20y%3D%2290%22%3E%22%20%27%20%23%20%25%20%26amp%3B%20" +
                            "%C2%BF%20%F0%9F%94%A3%3C%2Ftext%3E%3C%2Fsvg%3E%0A",
                            getLine(QUERY_LINE, canonicalRequest));
    }

    @Test
    public void testCharacterMapMatchesWithServer() {
        String queryParams = "a=-_.%7E%21*%27%28%29%3B%3A%40%26%3D%2B%24%2C%2F%3F%25%23%5B%5D%3F%40" +
                             "+ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        String canonicalRequest = canonicalizeWithQueryParams(queryParams);

        Assert.assertEquals("a=-_.~%21%2A%27%28%29%3B%3A%40%26%3D%2B%24%2C%2F%3F%25%23%5B%5D%3F%40" +
                            "%20ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789",
                            getLine(QUERY_LINE, canonicalRequest));
    }

    @Test
    public void testMultipleQueryParamsSortsParams() {
        String queryParams = "queryParam2=Hello+World&queryParam3=1%3D1&query+Param1=a*%7E%2F%3Db";

        String canonicalRequest = canonicalizeWithQueryParams(queryParams);

        Assert.assertEquals("query%20Param1=a%2A~%2F%3Db&queryParam2=Hello%20World&queryParam3=1%3D1",
                            getLine(QUERY_LINE, canonicalRequest));
    }

    @Test
    public void testMultipleQueryParamsSortsParamsWithIdenticalKey() {
        String queryParams = "param=value2&param=value1";

        String canonicalRequest = canonicalizeWithQueryParams(queryParams);

        Assert.assertEquals("param=value1&param=value2",
                            getLine(QUERY_LINE, canonicalRequest));
    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidUriPathPassedToRequestCanonicalizer() {
        String path = "/this is a bad path";

        canonicalizeWithPath(path);
    }

    @Test
    public void testNormalizesPath() {
        String path = "/v1/Workspaces/../test";

        String canonicalRequest = canonicalizeWithPath(path);

        Assert.assertEquals("/v1/test", getLine(PATH_LINE, canonicalRequest));
    }

    @Test
    public void testNormalizesPath2() {
        String path = "/v1/Workspaces/./test";

        String canonicalRequest = canonicalizeWithPath(path);

        Assert.assertEquals("/v1/Workspaces/test", getLine(PATH_LINE, canonicalRequest));
    }

    @Test
    public void testEmptyPath() {
        String path = "";

        String canonicalRequest = canonicalizeWithPath(path);

        Assert.assertEquals("/", getLine(PATH_LINE, canonicalRequest));
    }

    @Test
    public void testPathHasUnreservedCharacters() {
        String path = "/start%20*%7E+end";

        String canonicalRequest = canonicalizeWithPath(path);

        Assert.assertEquals("/start%20%2A~%2Bend",
                            getLine(PATH_LINE, canonicalRequest));
    }

    @Test
    public void testPathHasEncodedCharacters() {
        String path = "/v1/Workspaces/test()+%C3%A4/Workers";

        String canonicalRequest = canonicalizeWithPath(path);

        Assert.assertEquals("/v1/Workspaces/test%28%29%2B%C3%A4/Workers",
                            getLine(PATH_LINE, canonicalRequest));
    }

    @Test
    public void testPathHasNotEncodedCharactersAndControlCharacters() {
        String path = "/v1/Services/IS7f2d1594c3dd4d368d6669981a649659/Channels/é()+";

        String canonicalRequest = canonicalizeWithPath(path);

        Assert.assertEquals("/v1/Services/IS7f2d1594c3dd4d368d6669981a649659/Channels/%C3%A9%28%29%2B",
                            getLine(PATH_LINE, canonicalRequest));
    }

    @Test
    public void testPathHasEncodedCharactersAndControlCharacters() {
        String path = "/v1/Services/IS7f2d1594c3dd4d368d6669981a649659/Channels/%C3%A9()+";

        String canonicalRequest = canonicalizeWithPath(path);

        Assert.assertEquals("/v1/Services/IS7f2d1594c3dd4d368d6669981a649659/Channels/%C3%A9%28%29%2B",
                            getLine(PATH_LINE, canonicalRequest));
    }

    private String canonicalizeWithPath(String path) {
        return new RequestCanonicalizer("GET", path, "queryParam2=Hello+World", "foobar", headers).create(signedHeaders);
    }

    private String canonicalizeWithQueryParams(String queryParams) {
        return new RequestCanonicalizer("GET", "/Messages", queryParams, "foobar", headers).create(signedHeaders);
    }

    private String getLine(int lineNumber, String canonicalRequest) {
        return canonicalRequest.split("\n")[lineNumber];
    }
}
