package com.twilio.jwt.accesstoken;

import java.util.Map;

/**
 * Grant used to access Twilio Live.
 *
 * <p>
 *     For more information see:
 *     <a href="https://www.twilio.com/docs/api/rest/access-tokens">
 *         https://www.twilio.com/docs/api/rest/access-tokens
 *     </a>
 * </p>
 */
public class PlaybackGrant implements Grant {

    private Map<String, Object> grant;

    /**
     * Get the grant payload configured in this grant.
     * @return The grant payload or null if not set.
     */
    public Map<String, Object> getGrant() {
        return this.grant;
    }

    /**
     * Set the playback grant
     * @param grant the playback grant
     * @return updated PlaybackGrant instance
     */
    public PlaybackGrant setGrant(final Map<String, Object> grant) {
        this.grant = grant;
        return this;
    }

    public String getGrantKey() {
        return "player";
    }

    public Object getPayload() {
        return this.grant;
    }
}
