/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Oauth
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.oauth.v1;

import com.twilio.base.noauth.Creator;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Response;
import com.twilio.http.noauth.NoAuthRequest;
import com.twilio.http.noauth.NoAuthTwilioRestClient;
import com.twilio.rest.Domains;

public class TokenCreator extends Creator<Token> {

    private String grantType;
    private String clientId;
    private String clientSecret;
    private String code;
    private String redirectUri;
    private String audience;
    private String refreshToken;
    private String scope;

    public TokenCreator(final String grantType, final String clientId) {
        this.grantType = grantType;
        this.clientId = clientId;
    }

    public TokenCreator setGrantType(final String grantType) {
        this.grantType = grantType;
        return this;
    }

    public TokenCreator setClientId(final String clientId) {
        this.clientId = clientId;
        return this;
    }

    public TokenCreator setClientSecret(final String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    public TokenCreator setCode(final String code) {
        this.code = code;
        return this;
    }

    public TokenCreator setRedirectUri(final String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }

    public TokenCreator setAudience(final String audience) {
        this.audience = audience;
        return this;
    }

    public TokenCreator setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public TokenCreator setScope(final String scope) {
        this.scope = scope;
        return this;
    }

    @Override
    public Token create(final NoAuthTwilioRestClient client) {
        String path = "/v1/token";

        path = path.replace("{" + "GrantType" + "}", this.grantType.toString());
        path = path.replace("{" + "ClientId" + "}", this.clientId.toString());

        NoAuthRequest request = new NoAuthRequest(
            HttpMethod.POST,
            Domains.OAUTH.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException(
                "Token creation failed: Unable to connect to server"
            );
        } else if (
            !NoAuthTwilioRestClient.SUCCESS.test(response.getStatusCode())
        ) {
            RestException restException = RestException.fromJson(
                response.getStream(),
                client.getObjectMapper()
            );
            if (restException == null) {
                throw new ApiException(
                    "Server Error, no content",
                    response.getStatusCode()
                );
            }
            throw new ApiException(restException);
        }

        return Token.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final NoAuthRequest request) {
        if (grantType != null) {
            request.addPostParam("GrantType", grantType);
        }
        if (clientId != null) {
            request.addPostParam("ClientId", clientId);
        }
        if (clientSecret != null) {
            request.addPostParam("ClientSecret", clientSecret);
        }
        if (code != null) {
            request.addPostParam("Code", code);
        }
        if (redirectUri != null) {
            request.addPostParam("RedirectUri", redirectUri);
        }
        if (audience != null) {
            request.addPostParam("Audience", audience);
        }
        if (refreshToken != null) {
            request.addPostParam("RefreshToken", refreshToken);
        }
        if (scope != null) {
            request.addPostParam("Scope", scope);
        }
    }
}
