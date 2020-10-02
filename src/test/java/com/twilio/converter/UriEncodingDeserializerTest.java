package com.twilio.converter;

import com.twilio.exception.ApiException;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link UriEncodingDeserializerTest}.
 */
public class UriEncodingDeserializerTest {

    @Test
    public void testDeserialize() throws IOException {
        String json = "{ \"url\": \"http://test.test.com?param1=something I don't know&param2=test1234\" }";
        ObjectMapper mapper = new ObjectMapper();

        Container c = Container.fromJson(json, mapper);
        Assert.assertEquals("http://test.test.com?param1=something I don't know&param2=test1234",
            URLDecoder.decode(c.url.toString(), Charset.defaultCharset().toString()));
    }
    @Test
    public void testDeserializeNoQueryParams() throws IOException {
        String json = "{ \"url\": \"http://test.test.com\" }";
        ObjectMapper mapper = new ObjectMapper();

        Container c = Container.fromJson(json, mapper);
        Assert.assertEquals("http://test.test.com",
            URLDecoder.decode(c.url.toString(), Charset.defaultCharset().toString()));
    }

    @Test
    public void testDeserializeRawEncodedValueIsCorrect() throws IOException {
        String json = "{ \"url\": \"http://test.test.com?param=test param\" }";
        ObjectMapper mapper = new ObjectMapper();

        Container c = Container.fromJson(json, mapper);
        Assert.assertEquals("http://test.test.com?param=test+param",
            c.url.toString());
    }

    @Test
    public void testDeserializeRawEncodedValueIsCorrectWithExoticParams() throws IOException {
        String json = "{ \"url\": \"http://test.test.com?param=test param&params2=&param2=1 1\" }";
        ObjectMapper mapper = new ObjectMapper();

        Container c = Container.fromJson(json, mapper);
        Assert.assertEquals("http://test.test.com?param=test+param&params2=&param2=1+1",
            c.url.toString());
    }

    @Test
    public void testDeserializeRawEncodedValueIsCorrectWithCorrectlyEncodedParams() throws IOException {
        String json = "{ \"url\": \"http://test.test.com?param=test%20thing\"}";
        ObjectMapper mapper = new ObjectMapper();

        Container c = Container.fromJson(json, mapper);
        Assert.assertEquals("http://test.test.com?param=test%20thing",
            c.url.toString());

        json = "{ \"url\": \"http://test.test.com?param=test+thing\"}";

        c = Container.fromJson(json, mapper);
        Assert.assertEquals("http://test.test.com?param=test+thing",
            c.url.toString());
    }

    @Test(expected = JsonMappingException.class)
    public void testInvalidCurrency() throws IOException {
        String json = "{ \"url\": \"http:/totally junk nonsense not a url\" }";
        ObjectMapper mapper = new ObjectMapper();

        Container.fromJson(json, mapper);
    }

    private static class Container {
        private final URI url;

        public Container(
            @JsonProperty("url")
            @JsonDeserialize(using = UriEncodingDeserializer.class)
                URI url
        ) {
            this.url = url;
        }

        public static Container fromJson(String json, ObjectMapper mapper) throws IOException {
            return mapper.readValue(json, Container.class);
        }
    }

}
