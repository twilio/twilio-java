package com.twilio.exception;

import java.util.Map;

public class ApiException extends TwilioException {

    private static final long serialVersionUID = -3228320166955630014L;

    private final Integer code;
    private final String moreInfo;
    private final Integer status;
    private final Map<String, Object> details;
    private final Integer httpStatusCode;
    private final Object params;
    private final Boolean userError;

    /**
     * Create a new API Exception.
     *
     * @param message exception message
     */
    public ApiException(final String message) {
        this(message, null, null, null, null, null, null, null);
    }

    /**
     * Create a new API Exception.
     *
     * @param message exception message
     * @param cause cause of the exception
     */
    public ApiException(final String message, final Throwable cause) {
        this(message, null, null, null, null, null, null, cause);
    }

    /**
     * Create a new API Exception.
     *
     * @param message exception message
     * @param status status code
     */
    public ApiException(final String message, final Integer status) {
        this(message, null, null, status, null, null, null, null);
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
    public ApiException(final String message, final Integer code, final String moreInfo, final Integer status, final Integer httpStatusCode, final Object params, final Boolean userError,
                        final Throwable cause) {
        super(message, cause);
        this.code = code;
        this.moreInfo = moreInfo;
        this.status = status;
        this.details = null;
        this.httpStatusCode = httpStatusCode;
        this.params = params;
        this.userError = userError;
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
        this.httpStatusCode = restException.getHttpStatusCode();
        this.params = restException.getParams();
        this.userError = restException.getUserError();
    }

    /**
     * Create a new API Exception from RFC-9457 Problem Details.
     *
     * @param restStandardException  the rest standard exception
     */
    public ApiException(final RestStandardException restStandardException) {
        super(restStandardException.getTitle() + ": " + restStandardException.getDetail(), null);
        this.code = restStandardException.getCode();
        this.moreInfo = restStandardException.getType();
        this.status = restStandardException.getStatus();
        this.details = null;
        this.httpStatusCode = null;
        this.params = null;
        this.userError = null;
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

    public Object getParams() {
        return params;
    }

    public Boolean getUserError() {
        return userError;
    }
}
