/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Lookups
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.lookups.v2;

import com.twilio.base.Fetcher;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class PhoneNumberFetcher extends Fetcher<PhoneNumber> {

    private String pathPhoneNumber;
    private String fields;
    private String countryCode;
    private String firstName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String addressCountryCode;
    private String nationalId;
    private String dateOfBirth;
    private String lastVerifiedDate;
    private String verificationSid;
    private String partnerSubId;

    public PhoneNumberFetcher(final String pathPhoneNumber) {
        this.pathPhoneNumber = pathPhoneNumber;
    }

    public PhoneNumberFetcher setFields(final String fields) {
        this.fields = fields;
        return this;
    }

    public PhoneNumberFetcher setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public PhoneNumberFetcher setFirstName(final String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PhoneNumberFetcher setLastName(final String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PhoneNumberFetcher setAddressLine1(final String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public PhoneNumberFetcher setAddressLine2(final String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public PhoneNumberFetcher setCity(final String city) {
        this.city = city;
        return this;
    }

    public PhoneNumberFetcher setState(final String state) {
        this.state = state;
        return this;
    }

    public PhoneNumberFetcher setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public PhoneNumberFetcher setAddressCountryCode(
        final String addressCountryCode
    ) {
        this.addressCountryCode = addressCountryCode;
        return this;
    }

    public PhoneNumberFetcher setNationalId(final String nationalId) {
        this.nationalId = nationalId;
        return this;
    }

    public PhoneNumberFetcher setDateOfBirth(final String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public PhoneNumberFetcher setLastVerifiedDate(
        final String lastVerifiedDate
    ) {
        this.lastVerifiedDate = lastVerifiedDate;
        return this;
    }

    public PhoneNumberFetcher setVerificationSid(final String verificationSid) {
        this.verificationSid = verificationSid;
        return this;
    }

    public PhoneNumberFetcher setPartnerSubId(final String partnerSubId) {
        this.partnerSubId = partnerSubId;
        return this;
    }

    @Override
    public PhoneNumber fetch(final TwilioRestClient client) {
        String path = "/v2/PhoneNumbers/{PhoneNumber}";

        path =
            path.replace(
                "{" + "PhoneNumber" + "}",
                this.pathPhoneNumber.toString()
            );

        Request request = new Request(
            HttpMethod.GET,
            Domains.LOOKUPS.toString(),
            path
        );
        addQueryParams(request);
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException(
                "PhoneNumber fetch failed: Unable to connect to server"
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

        return PhoneNumber.fromJson(
            response.getStream(),
            client.getObjectMapper()
        );
    }

    private void addQueryParams(final Request request) {
        if (fields != null) {
            request.addQueryParam("Fields", fields);
        }
        if (countryCode != null) {
            request.addQueryParam("CountryCode", countryCode);
        }
        if (firstName != null) {
            request.addQueryParam("FirstName", firstName);
        }
        if (lastName != null) {
            request.addQueryParam("LastName", lastName);
        }
        if (addressLine1 != null) {
            request.addQueryParam("AddressLine1", addressLine1);
        }
        if (addressLine2 != null) {
            request.addQueryParam("AddressLine2", addressLine2);
        }
        if (city != null) {
            request.addQueryParam("City", city);
        }
        if (state != null) {
            request.addQueryParam("State", state);
        }
        if (postalCode != null) {
            request.addQueryParam("PostalCode", postalCode);
        }
        if (addressCountryCode != null) {
            request.addQueryParam("AddressCountryCode", addressCountryCode);
        }
        if (nationalId != null) {
            request.addQueryParam("NationalId", nationalId);
        }
        if (dateOfBirth != null) {
            request.addQueryParam("DateOfBirth", dateOfBirth);
        }
        if (lastVerifiedDate != null) {
            request.addQueryParam("LastVerifiedDate", lastVerifiedDate);
        }
        if (verificationSid != null) {
            request.addQueryParam("VerificationSid", verificationSid);
        }
        if (partnerSubId != null) {
            request.addQueryParam("PartnerSubId", partnerSubId);
        }
    }
}
