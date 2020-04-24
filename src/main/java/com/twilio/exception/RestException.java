package com.twilio.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Twilio Exceptions.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestException {

    private final Integer code;
    private final String message;
    private final String moreInfo;
    private final Integer status;
    private final Map<String, Object> details;

    /**
     * Initialize a Twilio Rest Exception.
     *
     * @param status HTTP status code
     * @param message message of exception
     * @param code Twilio status code
     * @param moreInfo further information, if there is any
     */
    @JsonCreator
    private RestException(@JsonProperty("status") final int status, @JsonProperty("message") final String message,
                          @JsonProperty("code") final Integer code, @JsonProperty("more_info") final String moreInfo,
                          @JsonProperty("details") final Map<String, Object> details) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.moreInfo = moreInfo;
        this.details = details;
    }

    /**
     * Build an exception from a JSON blob.
     *
     * @param json JSON blob
     * @param objectMapper JSON reader
     * @return Rest Exception as an object
     */
    public static RestException fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exception to Runtime
        try {
            return objectMapper.readValue(json, RestException.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public Integer getStatus() {
        return status;
    }

    public Map<String, Object> getDetails() {
        return details;
    }
}
