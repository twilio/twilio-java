package com.twilio.http;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.TwilioOauth;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.models.OAuthRequestBody;
import com.twilio.models.OAuthResponseBody;
import com.twilio.rest.content.v1.Content;

import java.io.IOException;
import java.io.InputStream;

public class OAuthTokenManager {
    private static OAuthTokenManager instance;
    private OAuthRequestBody oAuthRequestBody;
    private OAuthResponseBody oAuthResponseBody;
    
    private TwilioOAuthRestClient twilioOAuthRestClient = TwilioOauth.getRestClient();

    public static OAuthTokenManager getInstance() {
        if (instance == null) {
            synchronized (OAuthTokenManager.class) {
                if (instance == null) {
                    instance = new OAuthTokenManager();
                }
            }
        }
        return instance;
    }
    
    private OAuthTokenManager() { }
    
    public String fetchToken(OAuthRequest request) {
        if (oAuthResponseBody != null && !oAuthResponseBody.isAccessTokenExpired()) {
            System.out.println("Using existing token");
            return oAuthResponseBody.getAccessToken();
        }
        this.oAuthResponseBody = null;
        System.out.println("Fetching token from server");
        Response response = twilioOAuthRestClient.request(request);
        handleResponseExceptions(response);
        
        this.oAuthResponseBody = fromJson(response.getStream(), twilioOAuthRestClient.getObjectMapper());
        if (oAuthResponseBody.getAccessToken() == null || oAuthResponseBody.getAccessToken().isEmpty()) {
            throw new ApiException(
                    "OAuth access token is missing or invalid."
            );
        }
        return this.oAuthResponseBody.getAccessToken();
    }

    private void handleResponseExceptions(Response response) {
        if (response == null) {
            throw new ApiConnectionException(
                    "Content creation failed: Unable to connect to server"
            );
        } else if (!TwilioOAuthRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(
                    response.getStream(),
                    twilioOAuthRestClient.getObjectMapper()
            );
            if (restException == null) {
                throw new ApiException(
                        "Server Error, no content",
                        response.getStatusCode()
                );
            }
            throw new ApiException(restException);
        }
    }

    private OAuthResponseBody fromJson(
            final InputStream json,
            final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, OAuthResponseBody.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }
}
