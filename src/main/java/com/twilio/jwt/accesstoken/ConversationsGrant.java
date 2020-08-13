package com.twilio.jwt.accesstoken;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Grant used to access Twilio Conversations.
 *
 * @deprecated use {@link VideoGrant} instead.
 * <p>
 *     For more information see:
 *     <a href="https://www.twilio.com/docs/api/rest/access-tokens">
 *         https://www.twilio.com/docs/api/rest/access-tokens
 *     </a>
 * </p>
 */
@Deprecated
public class ConversationsGrant implements Grant {

    private String configurationProfileSid;

    public String getConfigurationProfileSid() {
        return configurationProfileSid;
    }

    public ConversationsGrant setConfigurationProfileSid(String configurationProfileSid) {
        this.configurationProfileSid = configurationProfileSid;
        return this;
    }

    public String getGrantKey() {
        return "rtc";
    }

    public Object getPayload() {
        return new Payload(this);
    }


    @SuppressWarnings("checkstyle:membername")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class Payload {
        public final String configuration_profile_sid;

        /**
         * Create the grant payload.
         *
         * @param grant Conversations grant
         */
        public Payload(ConversationsGrant grant) {
            this.configuration_profile_sid = grant.configurationProfileSid;
        }
    }
}
