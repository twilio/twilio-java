/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Flex
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.flexapi.v1;

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




public class InsightsQuestionnairesCategoryCreator extends Creator<InsightsQuestionnairesCategory>{
    private String name;
    private String authorization;

    public InsightsQuestionnairesCategoryCreator(final String name) {
        this.name = name;
    }

    public InsightsQuestionnairesCategoryCreator setName(final String name){
        this.name = name;
        return this;
    }
    public InsightsQuestionnairesCategoryCreator setAuthorization(final String authorization){
        this.authorization = authorization;
        return this;
    }

    @Override
    public InsightsQuestionnairesCategory create(final TwilioRestClient client){
        String path = "/v1/Insights/QualityManagement/Categories";

        path = path.replace("{"+"Name"+"}", this.name.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.FLEXAPI.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        addHeaderParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("InsightsQuestionnairesCategory creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return InsightsQuestionnairesCategory.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (name != null) {
            request.addPostParam("Name", name);
    
        }
    }
    private void addHeaderParams(final Request request) {
        if (authorization != null) {
            request.addHeaderParam("Authorization", authorization);

        }
    }
}
