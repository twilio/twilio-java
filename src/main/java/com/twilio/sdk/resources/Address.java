package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.creators.AddressCreator;
import com.twilio.sdk.deleters.AddressDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.AddressFetcher;

import com.twilio.sdk.readers.AddressReader;
import com.twilio.sdk.readers.DependentPhoneNumberReader;
import com.twilio.sdk.updaters.AddressUpdater;

import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;





import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Address extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final String city;
    private final DateTime dateUpdated;
    private final String region;
    private final String friendlyName;
    private final String uri;
    private final String accountSid;
    private final String street;
    private final String postalCode;
    private final String sid;
    private final DateTime dateCreated;
    private final String isoCountry;
    private final String customerName;
    
    

    @JsonCreator
    private Address(
        @JsonProperty("city") final String city,
        @JsonProperty("date_updated") final String dateUpdated,
        @JsonProperty("region") final String region,
        @JsonProperty("friendly_name") final String friendlyName,
        @JsonProperty("uri") final String uri,
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("street") final String street,
        @JsonProperty("postal_code") final String postalCode,
        @JsonProperty("sid") final String sid,
        @JsonProperty("date_created") final String dateCreated,
        @JsonProperty("iso_country") final String isoCountry,
        @JsonProperty("customer_name") final String customerName
        ) {
        this.city = city;
            this.dateUpdated = DateTime.parse(dateUpdated, Twilio.DATE_TIME_FORMATTER);
            this.region = region;
            this.friendlyName = friendlyName;
            this.uri = uri;
            this.accountSid = accountSid;
            this.street = street;
            this.postalCode = postalCode;
            this.sid = sid;
            this.dateCreated = DateTime.parse(dateCreated, Twilio.DATE_TIME_FORMATTER);
            this.isoCountry = isoCountry;
            this.customerName = customerName;
            
    }

    public static AddressCreator create(final String city, final String region, final String friendlyName, final String isoCountry, final String street, final String postalCode, final String customerName) {
        return new AddressCreator(city, region, friendlyName, isoCountry, street, postalCode, customerName);
    }

    public static AddressDeleter delete(final String sid) {
        return new AddressDeleter(sid);
    }

    public static AddressDeleter delete(final Address target) {
        return new AddressDeleter(target);
    }

    public static AddressFetcher fetch(final String sid) {
        return new AddressFetcher(sid);
    }

    public static AddressReader list() {
        return new AddressReader();
    }

    public static AddressUpdater update(final Address target) {
        return new AddressUpdater(target);
    }

    public static AddressUpdater update(final String sid) {
        return new AddressUpdater(sid);
    }

    public DependentPhoneNumberReader listDependentPhoneNumbers() {
        return new DependentPhoneNumberReader(this);
    }

    public static Address fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Address.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static Address fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Address.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public final String getCity() {
        return city;
    }
    public final DateTime getDateUpdated() {
        return dateUpdated;
    }
    public final String getRegion() {
        return region;
    }
    public final String getFriendlyName() {
        return friendlyName;
    }
    public final String getUri() {
        return uri;
    }
    public final String getAccountSid() {
        return accountSid;
    }
    public final String getStreet() {
        return street;
    }
    public final String getPostalCode() {
        return postalCode;
    }
    public final String getSid() {
        return sid;
    }
    public final DateTime getDateCreated() {
        return dateCreated;
    }
    public final String getIsoCountry() {
        return isoCountry;
    }
    public final String getCustomerName() {
        return customerName;
    }
    

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Address self = (Address) o;

        return (
            Objects.equals(city, self.city) &&
            Objects.equals(dateUpdated, self.dateUpdated) &&
            Objects.equals(region, self.region) &&
            Objects.equals(friendlyName, self.friendlyName) &&
            Objects.equals(uri, self.uri) &&
            Objects.equals(accountSid, self.accountSid) &&
            Objects.equals(street, self.street) &&
            Objects.equals(postalCode, self.postalCode) &&
            Objects.equals(sid, self.sid) &&
            Objects.equals(dateCreated, self.dateCreated) &&
            Objects.equals(isoCountry, self.isoCountry) &&
            Objects.equals(customerName, self.customerName) 
            );
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(
                city,
                dateUpdated,
                region,
                friendlyName,
                uri,
                accountSid,
                street,
                postalCode,
                sid,
                dateCreated,
                isoCountry,
                customerName
                );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                        .add("city", city)
                        .add("dateUpdated", dateUpdated)
                        .add("region", region)
                        .add("friendlyName", friendlyName)
                        .add("uri", uri)
                        .add("accountSid", accountSid)
                        .add("street", street)
                        .add("postalCode", postalCode)
                        .add("sid", sid)
                        .add("dateCreated", dateCreated)
                        .add("isoCountry", isoCountry)
                        .add("customerName", customerName)
                        .toString();
    }
}