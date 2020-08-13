package com.twilio.jwt.accesstoken;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Grant used to access Twilio TaskRouter.
 *
 * <p>
 *     For more information see:
 *     <a href="https://www.twilio.com/docs/api/rest/access-tokens">
 *         https://www.twilio.com/docs/api/rest/access-tokens
 *     </a>
 * </p>
 */
public class TaskRouterGrant implements Grant {

    private String workspaceSid;
    private String workerSid;
    private String role;

    public String getWorkspaceSid() {
        return workspaceSid;
    }

    public TaskRouterGrant setWorkspaceSid(String workspaceSid) {
        this.workspaceSid = workspaceSid;
        return this;
    }

    public String getWorkerSid() {
        return workerSid;
    }

    public TaskRouterGrant setWorkerSid(String workerSid) {
        this.workerSid = workerSid;
        return this;
    }

    public String getRole() {
        return role;
    }

    public TaskRouterGrant setRole(String role) {
        this.role = role;
        return this;
    }

    @Override
    public String getGrantKey() {
        return "task_router";
    }

    @Override
    public Object getPayload() {
        return new TaskRouterGrant.Payload(this);
    }

    @SuppressWarnings("checkstyle:membername")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class Payload {
        public final String workspace_sid;
        public final String worker_sid;
        public final String role;

        /**
         * Create the grant payload.
         *
         * @param grant TaskRouter grant
         */
        public Payload(TaskRouterGrant grant) {
            this.workspace_sid = grant.workspaceSid;
            this.worker_sid = grant.workerSid;
            this.role = grant.role;
        }
    }
}
