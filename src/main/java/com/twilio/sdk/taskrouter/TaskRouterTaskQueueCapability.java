package com.twilio.sdk.taskrouter;

public class TaskRouterTaskQueueCapability extends TaskRouterCapability {

    /**
     * Create a new Capability object to authorize taskqueue clients to interact
     * with the TaskRouter service.
     *
     * @param accountSid
     *            Account to authorize actions for
     * @param authToken
     *            Auth token for the account. Used to sign tokens and will not
     *            be included in the generated tokens.
     * @param workspaceSid
     *            Workspace to authorize tokens for.
     * @param taskQueueSid
     *            TaskQueue to create tokens for.
     */
    public TaskRouterTaskQueueCapability(final String accountSid, final String authToken, final String workspaceSid, final String taskQueueSid) {
        super(accountSid, authToken, workspaceSid, taskQueueSid);
    }

    @Override
    protected void setupResource() {
        this.resourceUrl = this.baseUrl + "/TaskQueues/" + this.channelId;
    }
}
