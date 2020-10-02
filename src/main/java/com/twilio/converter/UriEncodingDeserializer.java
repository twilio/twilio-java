package com.twilio.converter;

import com.twilio.exception.ApiException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * This class is for handling de-serializing string valued URLs into a URI object, even when that url isn't properly
 * URL encoded. Specifically, this handles the case of the url params not being encoded.
 * Example:
 * {
 *     "url" : "http://test.test.com?param=this is a test param
 * }
 *
 * will turn into:
 * {
 *    "url": "http://test.test.com?param=this+is+a+test+param
 * }
 * considerations:
 *  <ul>
 *      <li>All param variations should work, this includes duplicated params of the same name, and empty params.</li>
 *      <li>If, in the future, the response we get back changes to properly encode the url params of the url, this will correctly <b>not</b> double-encode that url.</li>
 *      <li>This de-serializer does <b>not</b> handle the case where some url params are encoded, and some are not. This will treat those as all un-encoded and re-encode them in order to force success of the URI.
 *      </li>
 *  </ul>
 *
 * Usage:
 * <pre>
 *      \@JsonProperty("voice_url")
 *      \@JsonDeserialize(using = com.twilio.converter.UriEncodingDeserializer.class)
 *      final URI voiceUrl
 * </pre>
 *
 */
public class UriEncodingDeserializer extends JsonDeserializer<URI> {

    @Override
    public URI deserialize(JsonParser jsonParser,
                                DeserializationContext deserializationContext) throws IOException {

        //if this fails, it's simply not a valid URL, will throw jsonMappingException
        final URL url = jsonParser.readValueAs(URL.class);
        final String queryParams = url.getQuery();

        URI encodedUrl;
        try {
            //we know it's a valid URL, so this should work. if this fails, we likely have a case of query params that
            //are not URL encoded.
            encodedUrl = url.toURI();
        }catch (URISyntaxException e){
            //This really shouldn't happen, as the url should be well formed (encoded) from Twilio, but let's try to
            // url encode the params anyway and see if that fixes the issue.
            try{
                String urlEncoded = url.getProtocol() + "://" + url.getHost() + url.getPath();
                if (queryParams != null && !queryParams.isEmpty()){
                    urlEncoded += "?" + splitEncodeAndReassembleQueryParams(queryParams);
                }
                encodedUrl =
                    new URL(urlEncoded).toURI();
            }catch(URISyntaxException use){
                //something unrecoverable is malformed in this url
                throw new ApiException(use.getMessage(), use);
            }
        }
        return encodedUrl;

    }

    private String splitEncodeAndReassembleQueryParams(final String queryParams){
        final String[] splitParams = queryParams.split("&");
        return Arrays.stream(splitParams).map(param -> {
            final String[] parts = param.split("=");
            if (parts.length == 2){
                try {
                    return parts[0] + "=" + URLEncoder.encode(parts[1], Charset.defaultCharset().toString());
                }catch(UnsupportedEncodingException ex){
                    throw new ApiException(ex.getMessage(), ex);
                }
            }
            return param;
        }).collect(Collectors.joining("&"));
    }
}