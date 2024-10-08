/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Accounts
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.accounts.v1;

import com.twilio.base.Creator;
import com.twilio.constant.EnumConstants;
import com.twilio.converter.Converter;
import com.twilio.converter.Converter;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import java.util.List;
import java.util.List;
import java.util.Map;
import java.util.Map;

public class BulkContactsCreator extends Creator<BulkContacts> {

    private List<Map<String, Object>> items;

    public BulkContactsCreator(final List<Map<String, Object>> items) {
        this.items = items;
    }

    public BulkContactsCreator setItems(final List<Map<String, Object>> items) {
        this.items = items;
        return this;
    }

    public BulkContactsCreator setItems(final Map<String, Object> items) {
        return setItems(Promoter.listOfOne(items));
    }

    @Override
    public BulkContacts create(final TwilioRestClient client) {
        String path = "/v1/Contacts/Bulk";

        path = path.replace("{" + "Items" + "}", this.items.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.ACCOUNTS.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException(
                "BulkContacts creation failed: Unable to connect to server"
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

        return BulkContacts.fromJson(
            response.getStream(),
            client.getObjectMapper()
        );
    }

    private void addPostParams(final Request request) {
        if (items != null) {
            for (Map<String, Object> prop : items) {
                request.addPostParam("Items", Converter.mapToJson(prop));
            }
        }
    }
}
