package com.twilio.jwt.accesstoken;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Grant used to access Twilio Sync.
 *
 * <p>
 *     For more information see:
 *     <a href="https://www.twilio.com/docs/api/rest/access-tokens">
 *         https://www.twilio.com/docs/api/rest/access-tokens
 *     </a>
 * </p>
 */
public class SyncGrant implements Grant {

    private String serviceSid;
    private String endpointId;

    public String getServiceSid() {
        return serviceSid;
    }

    public SyncGrant setServiceSid(String serviceSid) {
        this.serviceSid = serviceSid;
        return this;
    }

    public String getEndpointId() {
        return endpointId;
    }

    public SyncGrant setEndpointId(String endpointId) {
        this.endpointId = endpointId;
        return this;
    }

    @Override
    public String getGrantKey() {
        return "data_sync";
    }

    @Override
    public Object getPayload() {
        return new SyncGrant.Payload(this);
    }

    @SuppressWarnings("checkstyle:membername")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class Payload {
        public final String service_sid;
        public final String endpoint_id;

        /**
         * Create the grant payload.
         *
         * @param grant Sync grant
         */
        public Payload(SyncGrant grant) {
            this.service_sid = grant.serviceSid;
            this.endpoint_id = grant.endpointId;
        }
    }

}
