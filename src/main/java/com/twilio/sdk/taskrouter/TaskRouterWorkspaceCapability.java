package com.twilio.sdk.taskrouter;

public class TaskRouterWorkspaceCapability extends TaskRouterCapability {

    /**
     * Create a new Capability object to authorize workspace clients to interact
     * with the TaskRouter service.
     *
     * @param accountSid
     *            Account to authorize actions for
     * @param authToken
     *            Auth token for the account. Used to sign tokens and will not
     *            be included in the generated tokens.
     * @param workspaceSid
     *            Workspace to authorize tokens for.
     */
    public TaskRouterWorkspaceCapability(final String accountSid, final String authToken, final String workspaceSid) {
        super(accountSid, authToken, workspaceSid, workspaceSid);
    }

    @Override
    protected void setupResource() {
        this.resourceUrl = this.baseUrl;
    }

}
