/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Preview
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.preview.trustedComms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.exception.ApiConnectionException;

import com.twilio.exception.ApiException;

import lombok.ToString;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import java.util.Map;
import java.util.Objects;


import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class BrandedChannel extends Resource {
    private static final long serialVersionUID = 197274082532223L;


    public static BrandedChannelFetcher fetcher(final String sid){
        return new BrandedChannelFetcher(sid);
    }




    /**
    * Converts a JSON String into a BrandedChannel object using the provided ObjectMapper.
    *
    * @param json Raw JSON String
    * @param objectMapper Jackson ObjectMapper
    * @return BrandedChannel object represented by the provided JSON
    */
    public static BrandedChannel fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, BrandedChannel.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
    * Converts a JSON InputStream into a BrandedChannel object using the provided
    * ObjectMapper.
    *
    * @param json Raw JSON InputStream
    * @param objectMapper Jackson ObjectMapper
    * @return BrandedChannel object represented by the provided JSON
    */
    public static BrandedChannel fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, BrandedChannel.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String businessSid;
    private final String brandSid;
    private final String sid;
    private final Map<String, String> links;
    private final URI url;

    @JsonCreator
    private BrandedChannel(
        @JsonProperty("account_sid")
        final String accountSid,

        @JsonProperty("business_sid")
        final String businessSid,

        @JsonProperty("brand_sid")
        final String brandSid,

        @JsonProperty("sid")
        final String sid,

        @JsonProperty("links")
        final Map<String, String> links,

        @JsonProperty("url")
        final URI url
    ) {
        this.accountSid = accountSid;
        this.businessSid = businessSid;
        this.brandSid = brandSid;
        this.sid = sid;
        this.links = links;
        this.url = url;
    }

        public final String getAccountSid() {
            return this.accountSid;
        }
        public final String getBusinessSid() {
            return this.businessSid;
        }
        public final String getBrandSid() {
            return this.brandSid;
        }
        public final String getSid() {
            return this.sid;
        }
        public final Map<String, String> getLinks() {
            return this.links;
        }
        public final URI getUrl() {
            return this.url;
        }

    @Override
    public boolean equals(final Object o) {
        if (this==o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BrandedChannel other = (BrandedChannel) o;

        return Objects.equals(accountSid, other.accountSid) &&  Objects.equals(businessSid, other.businessSid) &&  Objects.equals(brandSid, other.brandSid) &&  Objects.equals(sid, other.sid) &&  Objects.equals(links, other.links) &&  Objects.equals(url, other.url)  ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid, businessSid, brandSid, sid, links, url);
    }

}
