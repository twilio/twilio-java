package com.twilio.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * RFC-9457 Problem Details Exception.
 *
 * Represents error responses compliant with RFC-9457 (Problem Details for HTTP APIs).
 * See https://www.rfc-editor.org/rfc/rfc9457.html for the specification.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestStandardException {

    private final String type;
    private final String title;
    private final Integer status;
    private final String detail;
    private final String instance;
    private final Integer code;
    private final List<ValidationError> errors;

    /**
     * Represents a validation error for a specific field.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ValidationError {
        private final String detail;
        private final String pointer;

        @JsonCreator
        public ValidationError(
                @JsonProperty("detail") final String detail,
                @JsonProperty("pointer") final String pointer) {
            this.detail = detail;
            this.pointer = pointer;
        }

        /**
         * Get the human-readable explanation of the validation error.
         *
         * @return validation error detail
         */
        public String getDetail() {
            return detail;
        }

        /**
         * Get the JSON Pointer (RFC 6901) to the location in the request where the error occurred.
         *
         * @return JSON pointer to the error location
         */
        public String getPointer() {
            return pointer;
        }
    }

    /**
     * Initialize an RFC-9457 Problem Details Exception.
     *
     * @param type     URI reference identifying the problem type
     * @param title    short, human-readable summary of the problem type
     * @param status   HTTP status code
     * @param detail   human-readable explanation specific to this occurrence
     * @param instance URI reference identifying the specific occurrence
     * @param code     Twilio-specific error code
     * @param errors   array of validation errors for HTTP 400/422 responses
     */
    @JsonCreator
    private RestStandardException(
            @JsonProperty("code") final Integer code,
            @JsonProperty("status") final Integer status,
            @JsonProperty("type") final String type,
            @JsonProperty("title") final String title,
            @JsonProperty("detail") final String detail,
            @JsonProperty("instance") final String instance,
            @JsonProperty("errors") final List<ValidationError> errors) {
        this.type = type;
        this.title = title;
        this.status = status;
        this.code = code;
        this.detail = detail == null ? "" : detail;
        this.instance = instance == null ? "" : instance;
        this.errors = errors == null ? Collections.emptyList() : errors;
    }

    /**
     * Build an exception from a JSON blob.
     *
     * @param json         JSON blob
     * @param objectMapper JSON reader
     * @return Problem Exception as an object
     */
    public static RestStandardException fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, RestStandardException.class);
        } catch (final DatabindException | StreamReadException e) {
            throw new ApiException(e.getMessage(), e);
        }
    }

    /**
     * Get the URI reference identifying the problem type.
     *
     * @return problem type URI
     */
    public String getType() {
        return type;
    }

    /**
     * Get the short, human-readable summary of the problem type.
     *
     * @return problem title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the HTTP status code.
     *
     * @return HTTP status code
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Get the human-readable explanation specific to this occurrence.
     *
     * @return problem detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Get the URI reference identifying the specific occurrence.
     *
     * @return instance URI
     */
    public String getInstance() {
        return instance;
    }

    /**
     * Get the Twilio-specific error code.
     *
     * @return Twilio error code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Get the array of validation errors for HTTP 400/422 responses.
     *
     * @return non-null list of validation errors (empty if none are present)
     */
    public List<ValidationError> getErrors() {
        return errors;
    }
}
