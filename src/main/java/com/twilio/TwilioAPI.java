package com.twilio;

import com.twilio.exception.AuthenticationException;
import com.twilio.exception.CertificateValidationException;
import com.twilio.http.*;

import java.util.concurrent.ExecutorService;

interface TwilioAPI {

    String VERSION = "9.0.0";
    String JAVA_VERSION = System.getProperty("java.version");

    /**
     * Set the username.
     *
     * @param username account to use
     * @throws AuthenticationException if username is null
     */
    void setUsername(final String username);

    /**
     * Set the auth token.
     *
     * @param password auth token to use
     * @throws AuthenticationException if password is null
     */
    void setPassword(final String password);

    /**
     * Set the account sid.
     *
     * @param accountSid account sid to use
     * @throws AuthenticationException if account sid is null
     */
    void setAccountSid(final String accountSid);

    /**
     * Set the region.
     *
     * @param region region to make request
     */
    void setRegion(final String region);

    /**
     * Set the edge.
     *
     * @param edge edge to make request
     */
    void setEdge(final String edge);

    /**
     * Attempts to gracefully shutdown the ExecutorService if it is present.
     */
    void destroy();

}
