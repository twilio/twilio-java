package com.twilio.http;

import com.twilio.exception.ApiException;
import org.apache.http.Header;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Response {

    private final InputStream stream;
    private String content;
    private final int statusCode;
    private final Header[] headers;

    /**
     * Create a Response from content string and status code.
     *
     * @param content    content string
     * @param statusCode status code
     */
    public Response(final String content, final int statusCode) {
        this(content, statusCode, null);
    }

    /**
     * Create a Response from content string, status code, and headers.
     *
     * @param content    content string
     * @param statusCode status code
     * @param headers    headers
     */
    public Response(final String content, final int statusCode, final Header[] headers) {
        this.stream = null;
        this.content = content;
        this.statusCode = statusCode;
        this.headers = headers;
    }

    /**
     * Create a Response from input stream and status code.
     *
     * @param stream     input stream
     * @param statusCode status code
     */
    public Response(final InputStream stream, final int statusCode) {
        this(stream, statusCode, null);
    }

    /**
     * Create a Response from input stream, status code, and headers.
     *
     * @param stream     input stream
     * @param statusCode status code
     * @param headers    headers
     */
    public Response(final InputStream stream, final int statusCode, final Header[] headers) {
        this.stream = stream;
        this.content = null;
        this.statusCode = statusCode;
        this.headers = headers;
    }

    /**
     * Get the the content of the response.
     *
     * <p>
     * If there is a content string, that will be returned.
     * Otherwise, will get content from input stream
     * </p>
     *
     * @return the content string
     */
    public String getContent() {
        if (content != null) {
            return content;
        }

        if (stream != null) {
            Scanner scanner = new Scanner(stream, "UTF-8").useDelimiter("\\A");

            if (!scanner.hasNext()) {
                return "";
            }

            content = scanner.next();
            scanner.close();

            return content;
        }

        return "";
    }

    /**
     * Get response data as stream.
     *
     * @return the response data as a stream
     */
    public InputStream getStream() {
        if (stream != null) {
            return stream;
        }
        try {
            return new ByteArrayInputStream(content.getBytes("utf-8"));
        } catch (final UnsupportedEncodingException e) {
            throw new ApiException("UTF-8 encoding not supported", e);
        }
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Header[] getHeaders() {
        return headers;
    }
}
