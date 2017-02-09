package com.twilio.jwt.accesstoken;

public interface Grant {

    /**
     * The key for the grant.
     *
     * @return The key for the grant.
     */
    public String getGrantKey();

    /**
     * The payload for this grant.
     *
     * <p>The payload allows us to decouple the user API from how the grant is structured.</p>
     *
     * @return The payload for this grant.
     */
    public Object getPayload();

}
