package com.twilio.exception;

import java.util.Map;

public class ApiException extends TwilioException {

    private static final long serialVersionUID = -3228320166955630014L;

    private final Integer code;
    private String moreInfo;
    private Integer status;
    private Map<String, Object> details;
    private Integer httpStatusCode;
    private Boolean userError;
    private Map<String, Object> params;

    /**
     * Create a new API Exception.
     *
     * @param message exception message
     */
    public ApiException(final String message) {
        this(message, null, null, null, null);
    }

    /**
     * Create a new API Exception.
     *
     * @param message exception message
     * @param cause cause of the exception
     */
    public ApiException(final String message, final Throwable cause) {
        this(message, null, null, null, cause);
    }

    /**
     * Create a new API Exception.
     *
     * @param message exception message
     * @param status status code
     */
    public ApiException(final String message, final Integer status) {
        this(message, null, null, status, null);
    }


    /**
     * Create a new API Exception.
     *
     * @param message exception message
     * @param code exception code
     * @param moreInfo more information if available
     * @param status status code
     * @param cause cause of the exception* @param cause
     */
    public ApiException(final String message, final Integer code, final String moreInfo, final Integer status,
                        final Throwable cause) {
        super(message, cause);
        this.code = code;
        this.moreInfo = moreInfo;
        this.status = status;
        this.details = null;
    }


    public ApiException(final Integer code, final String message, final Integer httpStatusCode, final Boolean userError,
        final Throwable cause, String moreInfo, Integer status, Map<String, Object> details) {
        super(message, cause);
        this.code = code;
        this.httpStatusCode = httpStatusCode;
        this.userError = userError;
        this.moreInfo = moreInfo;
        this.status = status;
        this.details = details;
        this.params = null;
    }
    /**
     * Create a new API Exception.
     *
     * @param restException  the rest exception
     */
    public ApiException(final RestException restException) {
        super(restException.getMessage(), null);
        this.code = restException.getCode();
        this.moreInfo = restException.getMoreInfo();
        this.status = restException.getStatus();
        this.details = restException.getDetails();
    }

    /**
     * Create V1.0 standard Rest Exception
     * @return restException as RestExceptionV10
     */
    public ApiException(final RestExceptionV10 restException) {
        super(restException.getMessage(), null);
        this.code = restException.getCode();
        this.httpStatusCode = restException.getHttpStatusCode();
        this.userError = restException.getUserError();
        this.params = restException.getParams();
    }

    public Integer getCode() {
        return code;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public Integer getStatusCode() {
        return status;
    }

    public Map<String, Object> getDetails() {
        return details;
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
