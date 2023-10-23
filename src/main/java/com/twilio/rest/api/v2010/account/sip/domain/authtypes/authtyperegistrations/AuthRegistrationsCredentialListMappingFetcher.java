/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Api
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.api.v2010.account.sip.domain.authtypes.authtyperegistrations;

import com.twilio.base.Fetcher;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;




public class AuthRegistrationsCredentialListMappingFetcher extends Fetcher<AuthRegistrationsCredentialListMapping> {
    private String pathDomainSid;
    private String pathSid;
    private String pathAccountSid;

    public AuthRegistrationsCredentialListMappingFetcher(final String pathDomainSid, final String pathSid){
        this.pathDomainSid = pathDomainSid;
        this.pathSid = pathSid;
    }
    public AuthRegistrationsCredentialListMappingFetcher(final String pathAccountSid, final String pathDomainSid, final String pathSid){
        this.pathAccountSid = pathAccountSid;
        this.pathDomainSid = pathDomainSid;
        this.pathSid = pathSid;
    }


    @Override
    public AuthRegistrationsCredentialListMapping fetch(final TwilioRestClient client) {
        String path = "/2010-04-01/Accounts/{AccountSid}/SIP/Domains/{DomainSid}/Auth/Registrations/CredentialListMappings/{Sid}.json";

        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        path = path.replace("{"+"AccountSid"+"}", this.pathAccountSid.toString());
        path = path.replace("{"+"DomainSid"+"}", this.pathDomainSid.toString());
        path = path.replace("{"+"Sid"+"}", this.pathSid.toString());

        Request request = new Request(
            HttpMethod.GET,
            Domains.API.toString(),
            path
        );
        Response response = client.request(request);

        if (response == null) {
        throw new ApiConnectionException("AuthRegistrationsCredentialListMapping fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content", response.getStatusCode());
            }
            throw new ApiException(restException);
        }

        return AuthRegistrationsCredentialListMapping.fromJson(response.getStream(), client.getObjectMapper());
    }
}
