package com.twilio.sdk.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Response {
    private InputStream stream;
    private String content;
    private int statusCode;

    public Response(String content, int statusCode) {
        this.content = content;
        this.stream = null;
        this.statusCode = statusCode;
    }

    public Response(InputStream stream, int statusCode) {
        this.stream = stream;
        this.content = null;
        this.statusCode = statusCode;
    }

    public String getContent() {
        if (this.content != null) {
            return content;
        }
        // XXX we probably don't need this and should convert strings into
        // streams in the mock scaffolding
        try {
            if (this.stream.available() == 0) {
                return "";
            }
        } catch (IOException e) {
            throw new RuntimeException("whoops"); // XXX
        }
        return (new Scanner(this.stream, "UTF-8").useDelimiter("\\A")).next();

    }

    public InputStream getStream() {
        if (this.stream != null) {
            return this.stream;
        }
        try {
            return new ByteArrayInputStream(this.content.getBytes("utf-8"));
        } catch(UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 encoding not supported?");
        }
    }

    public int getStatusCode() {
        return statusCode;
    }
}
