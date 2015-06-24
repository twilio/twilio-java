package com.twilio.sdk.taskrouter;

public class TaskRouterTaskQueueCapability extends TaskRouterCapability {

    public TaskRouterTaskQueueCapability(final String accountSid, final String authToken, final String workspaceSid, final String taskQueueSid) throws Exception {
        super(accountSid, authToken, workspaceSid, taskQueueSid, null);
    }

}
