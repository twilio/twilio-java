package com.twilio.jwt.accesstoken;

import com.google.common.base.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Grant used to access Twilio Voice
 *
 * <p>
 *     For more information see:
 *     <a href="https://www.twilio.com/docs/api/rest/access-tokens">
 *         https://www.twilio.com/docs/api/rest/access-tokens
 *     </a>
 * </p>
 */
public class VoiceGrant implements Grant {

    private Boolean incomingAllow;
    private String outgoingApplicationSid;
    private Map<String, Object> outgoingApplicationParams;
    private String pushCredentialSid;
    private String endpointId;

    public VoiceGrant setIncomingAllow(Boolean incomingAllow) {
      this.incomingAllow = incomingAllow;
      return this;
    }

    public VoiceGrant setOutgoingApplicationSid(String outgoingApplicationSid) {
        this.outgoingApplicationSid = outgoingApplicationSid;
        return this;
    }

    /**
     * Set the outgoing application.
     * 
     * @param  outgoingApplicationSid outgoing application sid
     * @param  outgoingApplicationParams outgoing application parameters
     * @return voice grant
     */
    public VoiceGrant setOutgoingApplication(
        String outgoingApplicationSid,
        Map<String, Object> outgoingApplicationParams
    ) {
        this.outgoingApplicationSid = outgoingApplicationSid;
        this.outgoingApplicationParams = outgoingApplicationParams;
        return this;
    }

    public VoiceGrant setPushCredentialSid(String pushCredentialSid) {
        this.pushCredentialSid = pushCredentialSid;
        return this;
    }

    public VoiceGrant setEndpointId(String endpointId) {
        this.endpointId = endpointId;
        return this;
    }

    @Override
    public String getGrantKey() {
        return "voice";
    }

    @Override
    public Object getPayload() {
        return new Payload(this);
    }

    @SuppressWarnings("checkstyle:membername")
    public class Payload {
        public Map<String, Object> incoming;
        public Map<String, Object> outgoing;
        public String push_credential_sid;
        public String endpoint_id;

        /**
         * Generate VoiceGrant payload.
         * 
         * @param  grant VoiceGrant
         */
        public Payload(VoiceGrant grant) {
            if (grant.incomingAllow == true) {
              this.incoming = new HashMap<>();
              this.incoming.put("allow", true);
            }

            if (!Strings.isNullOrEmpty(grant.outgoingApplicationSid)) {
                this.outgoing = new HashMap<>();
                this.outgoing.put("application_sid", grant.outgoingApplicationSid);

                if (grant.outgoingApplicationParams != null) {
                    this.outgoing.put("params", grant.outgoingApplicationParams);
                }
            }

            if (!Strings.isNullOrEmpty(grant.pushCredentialSid)) {
                this.push_credential_sid = grant.pushCredentialSid;
            }

            if (!Strings.isNullOrEmpty(grant.endpointId)) {
                this.endpoint_id = grant.endpointId;
            }
        }
    }

}
