/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Bulk Messaging and Broadcast
 * Bulk Sending is a public Twilio REST API for 1:Many Message creation up to 100 recipients. Broadcast is a public Twilio REST API for 1:Many Message creation up to 10,000 recipients via file upload.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.previewmessaging.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Creator;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;




public class MessageCreator extends Creator<Message>{
    private Message.CreateMessagesRequest createMessagesRequest;

    public MessageCreator(final Message.CreateMessagesRequest createMessagesRequest) {
        this.createMessagesRequest = createMessagesRequest;
    }

    public MessageCreator setCreateMessagesRequest(final Message.CreateMessagesRequest createMessagesRequest){
        this.createMessagesRequest = createMessagesRequest;
        return this;
    }

    @Override
    public Message create(final TwilioRestClient client){
        String path = "/v1/Messages";

        path = path.replace("{"+"CreateMessagesRequest"+"}", this.createMessagesRequest.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.PREVIEWMESSAGING.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.JSON);
        addPostParams(request, client);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("Message creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content", response.getStatusCode());
            }
            throw new ApiException(restException);
        }

        return Message.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request, TwilioRestClient client) {
        ObjectMapper objectMapper = client.getObjectMapper();
        if (createMessagesRequest != null) {
            request.setBody(Message.toJson(createMessagesRequest, objectMapper));
        }
    }
}
