package com.twilio.sdk.http;

import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Response {

    private final InputStream stream;
    private final String content;
    private final int statusCode;

    public Response(final String content, final int statusCode) {
        this.content = content;
        stream = null;
        this.statusCode = statusCode;
    }

    public Response(final InputStream stream, final int statusCode) {
        this.stream = stream;
        content = null;
        this.statusCode = statusCode;
    }

    public String getContent() {
        if (content != null) {
            return content;
        }
        // XXX we probably don't need this and should convert strings into
        // streams in the mock scaffolding
        try {
            if (stream.available() == 0) {
                return "";
            }
        } catch (final IOException e) {
            throw new ApiConnectionException("IOException during API request to Twilio", e);
        }
        return (new Scanner(stream, "UTF-8").useDelimiter("\\A")).next();

    }

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
}
