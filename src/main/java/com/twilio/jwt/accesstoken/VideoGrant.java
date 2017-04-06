package com.twilio.jwt.accesstoken;

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

    public String configurationProfileSid;

    public String getConfigurationProfileSid() {
        return configurationProfileSid;
    }

    public VideoGrant setConfigurationProfileSid(String configurationProfileSid) {
        this.configurationProfileSid = configurationProfileSid;
        return this;
    }

    public String getGrantKey() {
        return "video";
    }

    public Object getPayload() {
        return new Payload(this);
    }

    @SuppressWarnings("checkstyle:membername")
    public class Payload {
        public final String configuration_profile_sid;

        public Payload(VideoGrant grant) {
            this.configuration_profile_sid = grant.configurationProfileSid;
        }
    }
}
