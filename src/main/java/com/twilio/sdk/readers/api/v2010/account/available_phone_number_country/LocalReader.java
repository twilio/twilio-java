package com.twilio.sdk.readers.api.v2010.account.available_phone_number_country;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.Reader;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.Local;

public class LocalReader extends Reader<Local> {
    private final String accountSid;
    private final String countryCode;
    private Integer areaCode;
    private String contains;
    private Boolean smsEnabled;
    private Boolean mmsEnabled;
    private Boolean voiceEnabled;
    private Boolean excludeAllAddressRequired;
    private Boolean excludeLocalAddressRequired;
    private Boolean excludeForeignAddressRequired;
    private Boolean beta;

    /**
     * Construct a new LocalReader
     * 
     * @param accountSid The account_sid
     * @param countryCode The country_code
     */
    public LocalReader(final String accountSid, final String countryCode) {
        this.accountSid = accountSid;
        this.countryCode = countryCode;
    }

    /**
     * The area_code
     * 
     * @param areaCode The area_code
     * @return this
     */
    public LocalReader byAreaCode(final Integer areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    /**
     * The contains
     * 
     * @param contains The contains
     * @return this
     */
    public LocalReader byContains(final String contains) {
        this.contains = contains;
        return this;
    }

    /**
     * The sms_enabled
     * 
     * @param smsEnabled The sms_enabled
     * @return this
     */
    public LocalReader bySmsEnabled(final Boolean smsEnabled) {
        this.smsEnabled = smsEnabled;
        return this;
    }

    /**
     * The mms_enabled
     * 
     * @param mmsEnabled The mms_enabled
     * @return this
     */
    public LocalReader byMmsEnabled(final Boolean mmsEnabled) {
        this.mmsEnabled = mmsEnabled;
        return this;
    }

    /**
     * The voice_enabled
     * 
     * @param voiceEnabled The voice_enabled
     * @return this
     */
    public LocalReader byVoiceEnabled(final Boolean voiceEnabled) {
        this.voiceEnabled = voiceEnabled;
        return this;
    }

    /**
     * The exclude_all_address_required
     * 
     * @param excludeAllAddressRequired The exclude_all_address_required
     * @return this
     */
    public LocalReader byExcludeAllAddressRequired(final Boolean excludeAllAddressRequired) {
        this.excludeAllAddressRequired = excludeAllAddressRequired;
        return this;
    }

    /**
     * The exclude_local_address_required
     * 
     * @param excludeLocalAddressRequired The exclude_local_address_required
     * @return this
     */
    public LocalReader byExcludeLocalAddressRequired(final Boolean excludeLocalAddressRequired) {
        this.excludeLocalAddressRequired = excludeLocalAddressRequired;
        return this;
    }

    /**
     * The exclude_foreign_address_required
     * 
     * @param excludeForeignAddressRequired The exclude_foreign_address_required
     * @return this
     */
    public LocalReader byExcludeForeignAddressRequired(final Boolean excludeForeignAddressRequired) {
        this.excludeForeignAddressRequired = excludeForeignAddressRequired;
        return this;
    }

    /**
     * The beta
     * 
     * @param beta The beta
     * @return this
     */
    public LocalReader byBeta(final Boolean beta) {
        this.beta = beta;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Local ResourceSet
     */
    @Override
    public ResourceSet<Local> execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            "/2010-04-01/Accounts/" + this.accountSid + "/AvailablePhoneNumbers/" + this.countryCode + "/Local.json",
            client.getAccountSid()
        );
        
        addQueryParams(request);
        
        Page<Local> page = pageForRequest(client, request);
        
        return new ResourceSet<>(this, client, page);
    }

    /**
     * Retrieve the next page from the Twilio API
     * 
     * @param nextPageUri URI from which to retrieve the next page
     * @param client TwilioRestClient with which to make the request
     * @return Next Page
     */
    @Override
    public Page<Local> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            nextPageUri,
            client.getAccountSid()
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Local Resources for a given request
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    protected Page<Local> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Local read failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        Page<Local> result = new Page<>();
        result.deserialize("available_phone_numbers", response.getContent(), Local.class, client.getObjectMapper());
        
        return result;
    }

    /**
     * Add the requested query string arguments to the Request
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (areaCode != null) {
            request.addQueryParam("AreaCode", areaCode.toString());
        }
        
        if (contains != null) {
            request.addQueryParam("Contains", contains);
        }
        
        if (smsEnabled != null) {
            request.addQueryParam("SmsEnabled", smsEnabled.toString());
        }
        
        if (mmsEnabled != null) {
            request.addQueryParam("MmsEnabled", mmsEnabled.toString());
        }
        
        if (voiceEnabled != null) {
            request.addQueryParam("VoiceEnabled", voiceEnabled.toString());
        }
        
        if (excludeAllAddressRequired != null) {
            request.addQueryParam("ExcludeAllAddressRequired", excludeAllAddressRequired.toString());
        }
        
        if (excludeLocalAddressRequired != null) {
            request.addQueryParam("ExcludeLocalAddressRequired", excludeLocalAddressRequired.toString());
        }
        
        if (excludeForeignAddressRequired != null) {
            request.addQueryParam("ExcludeForeignAddressRequired", excludeForeignAddressRequired.toString());
        }
        
        if (beta != null) {
            request.addQueryParam("Beta", beta.toString());
        }
        
        request.addQueryParam("PageSize", Integer.toString(getPageSize()));
    }
}