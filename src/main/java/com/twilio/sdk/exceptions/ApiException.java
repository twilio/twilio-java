package com.twilio.sdk.exceptions;

public class ApiException extends TwilioException {

    private static final long serialVersionUID = -3228320166955630014L;

    private final Integer code;
    private final String moreInfo;
    private final Integer status;

    public ApiException(final String message) {
        this(message, null, null, null, null);
    }

    public ApiException(final String message, final Throwable cause) {
        this(message, null, null, null, cause);
    }

    public ApiException(final String message, final Integer code, final String moreInfo, final Integer status,
                        final Throwable cause) {
        super(message, cause);
        this.code = code;
        this.moreInfo = moreInfo;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public int getStatusCode() {
        return status;
    }
}
