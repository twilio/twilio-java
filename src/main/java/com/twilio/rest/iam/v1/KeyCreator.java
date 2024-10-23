/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Iam
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.iam.v1;

import com.twilio.base.Creator;
import com.twilio.constant.EnumConstants;
import com.twilio.converter.Converter;
import com.twilio.converter.Converter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import java.util.Map;
import java.util.Map;

public class KeyCreator extends Creator<Key> {

    private String accountSid;
    private String friendlyName;
    private Key.Keytype keyType;
    private Map<String, Object> policy;

    public KeyCreator(final String accountSid) {
        this.accountSid = accountSid;
    }

    public KeyCreator setAccountSid(final String accountSid) {
        this.accountSid = accountSid;
        return this;
    }

    public KeyCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    public KeyCreator setKeyType(final Key.Keytype keyType) {
        this.keyType = keyType;
        return this;
    }

    public KeyCreator setPolicy(final Map<String, Object> policy) {
        this.policy = policy;
        return this;
    }

    @Override
    public Key create(final TwilioRestClient client) {
        String path = "/v1/Keys";

        path =
            path.replace("{" + "AccountSid" + "}", this.accountSid.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.IAM.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException(
                "Key creation failed: Unable to connect to server"
            );
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
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

        return Key.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        if (accountSid != null) {
            request.addPostParam("AccountSid", accountSid);
        }
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
        if (keyType != null) {
            request.addPostParam("KeyType", keyType.toString());
        }
        if (policy != null) {
            request.addPostParam("Policy", Converter.mapToJson(policy));
        }
    }
}
