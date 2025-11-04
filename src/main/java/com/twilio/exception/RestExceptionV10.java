package com.twilio.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Rest Exception V1.0 for Twilio API Standards
 */
public class RestExceptionV10 {

  private final Integer code;
  private final String message;
  private final Integer httpStatusCode;
  private final Boolean userError;
  private final Map<String, Object> params;

  /**
   * Initialize a Twilio Rest Exception.
   *
   * @param code Twilio-specific error code
   * @param message A human readable error message
   * @param httpStatusCode HTTP response status code
   * @param userError whether the error is a user error (true) or a system error (false)
   * @param params A map of parameters related to the error, for example, a `params.twilioErrorCodeUrl` might hold a URL or link to additional information
   */
  @JsonCreator
  private RestExceptionV10(@JsonProperty("code") final int code, @JsonProperty("message") final String message,
      @JsonProperty("httpStatusCode") final Integer httpStatusCode, @JsonProperty("userError") final boolean userError,
      @JsonProperty("params") final Map<String, Object> params) {
    this.code = code;
    this.message = message;
    this.httpStatusCode = httpStatusCode;
    this.userError = userError;
    this.params = params;
  }

  /**
   * Build an exception from a JSON blob.
   *
   * @param json JSON blob
   * @param objectMapper JSON reader
   * @return Rest Exception as an object
   */
  public static RestExceptionV10 fromJson(final InputStream json, final ObjectMapper objectMapper) {
    // Convert all checked exception to Runtime
    try {
      return objectMapper.readValue(json, RestExceptionV10.class);
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

  public Integer getHttpStatusCode() {
    return httpStatusCode;
  }

  public Boolean getUserError() {
    return userError;
  }

  public Map<String, Object> getParams() {
    return params;
  }
}
