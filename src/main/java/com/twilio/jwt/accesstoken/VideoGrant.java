package com.twilio.jwt.accesstoken;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Grant used to access Twilio Video.
 *
 * <p>
 *     For more information see:
 *     <a href="https://www.twilio.com/docs/api/rest/access-tokens">
 *         https://www.twilio.com/docs/api/rest/access-tokens
 *     </a>
 * </p>
 */
public class VideoGrant implements Grant {

    private String configurationProfileSid;
    private String room;

    /**
     * Get the configuration profile sid of this grant.
     * @deprecated use {@link #getRoom()} instead.
     * @return The configuration profile sid or null if not set.
     */
    @Deprecated
    public String getConfigurationProfileSid() {
        return this.configurationProfileSid;
    }

    /**
     * Set the configuration profile sid on the grant.
     * @deprecated use {@link #setRoom(String)} instead.
     * @param configurationProfileSid configuration profile sid
     * @return updated VideoGrant instance
     */
    @Deprecated
    public VideoGrant setConfigurationProfileSid(final String configurationProfileSid) {
        this.configurationProfileSid = configurationProfileSid;
        return this;
    }

    /**
     * Get the room configured in this grant.
     * @return The room name or sid or null if not set.
     */
    public String getRoom() {
        return this.room;
    }

    /**
     * Set the room to grant access to
     * @param roomSidOrName a room sid or name
     * @return updated VideoGrant instance
     */
    public VideoGrant setRoom(final String roomSidOrName) {
        this.room = roomSidOrName;
        return this;
    }

    public String getGrantKey() {
        return "video";
    }

    public Object getPayload() {
        return new Payload(this);
    }


    @SuppressWarnings("checkstyle:membername")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public class Payload {
        public final String configuration_profile_sid;
        public final String room;

        public Payload(VideoGrant grant) {
            this.configuration_profile_sid = grant.configurationProfileSid;
            this.room = grant.room;
        }
    }
}
