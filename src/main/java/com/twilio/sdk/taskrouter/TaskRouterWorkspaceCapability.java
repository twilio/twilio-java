package com.twilio.sdk.taskrouter;

public class TaskRouterWorkspaceCapability extends TaskRouterCapability {

    public TaskRouterWorkspaceCapability(final String accountSid, final String authToken, final String workspaceSid) throws Exception {
        super(accountSid, authToken, workspaceSid, workspaceSid, null);

    }

}
