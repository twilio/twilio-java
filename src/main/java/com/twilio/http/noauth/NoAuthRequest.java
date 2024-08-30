package com.twilio.http.noauth;

import com.twilio.http.HttpMethod;
import com.twilio.http.IRequest;

public class NoAuthRequest extends IRequest {
    
    public NoAuthRequest(HttpMethod method, String url) {
        super(method, url);
    }

    public NoAuthRequest(HttpMethod method, String domain, String uri) {
        super(method, domain, uri, null);
    }

    public NoAuthRequest(HttpMethod method, String domain, String uri, String region) {
        super(method, domain, uri, region);
    }
}
