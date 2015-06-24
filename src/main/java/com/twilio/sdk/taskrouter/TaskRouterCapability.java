package com.twilio.sdk.taskrouter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskRouterCapability extends CapabilityAPI {

    private final static String TASKROUTER_BASE_URL = "https://taskrouter.twilio.com";
    private final static String TASKROUTER_VERSION = "v1";
    private final static String TASKROUTER_EVENT_URL = "https://event-bridge.twilio.com/v1/wschannels";

    protected String accountSid;
    protected String authToken;
    protected String workspaceSid;
    protected String channelId;
    protected String resourceUrl;
    protected List<Policy> policies;
    protected String baseUrl;

    public TaskRouterCapability(final String accountSid, final String authToken, final String workspaceSid, final String channelId, String resourceUrl) throws Exception {
        super(accountSid, authToken, TASKROUTER_VERSION, channelId);
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.workspaceSid = workspaceSid;
        this.channelId = channelId;
        this.policies = new ArrayList<Policy>();
        this.baseUrl = TASKROUTER_BASE_URL + "/" + TASKROUTER_VERSION + "/Workspaces/" + workspaceSid;
        if (resourceUrl == null) {
            if (channelId.substring(0, 2).equals("WS")) {
                resourceUrl = this.baseUrl;
            } else if (channelId.substring(0, 2).equals("WK")) {
                resourceUrl = this.baseUrl + "/Workers/" + channelId;
            } else if (channelId.substring(0, 2).equals("WQ")) {
                resourceUrl = this.baseUrl + "/TaskQueues/" + channelId;
            }
        }

        this.resourceUrl = resourceUrl;
        // add permissions to GET and POST to the event-bridge channel
        addTaskRouterPolicies(channelId);
        this.addPolicy(new Policy(resourceUrl, "GET", true));
        validateJWT();
    }

    private void addTaskRouterPolicies(final String channelId) {
        // Workers can GET and POST their own events
        final String eventBridgeUrl = TASKROUTER_EVENT_URL + "/" + accountSid + "/" + channelId;
        addPolicy(new Policy(eventBridgeUrl, "GET", true));
        addPolicy(new Policy(eventBridgeUrl, "POST", true));
    }

    private void validateJWT() throws Exception {
        if (accountSid == null || !accountSid.substring(0, 2).equals("AC")) {
            throw new Exception("Invalid AccountSid provided: " + accountSid);
        }
        if (workspaceSid == null || !workspaceSid.substring(0, 2).equals("WS")) {
            throw new Exception("Invalid WorkspaceSid provided: " + workspaceSid);
        }
        if (channelId == null) {
            throw new Exception("ChannelId not provided");
        }
        final String prefix = channelId.substring(0, 2);
        if (!prefix.equals("WS") && !prefix.equals("WK") && !prefix.equals("WQ")) {
            throw new Exception("Invalid ChannelId provided: " + channelId);
        }
    }

    public void allowFetchSubresources() {
        this.allow(this.resourceUrl + "/**", "GET", null, null);
    }

    public void allowUpdates() {
        this.allow(this.resourceUrl, "POST", null, null);
    }

    public void allowUpdatesSubresources() {
        this.allow(this.resourceUrl + "/**", "POST", null, null);
    }

    public void allowDelete() {
        this.allow(this.resourceUrl, "DELETE", null, null);
    }

    public void allowDeleteSubresources() {
        this.allow(this.resourceUrl + "/**", "DELETE", null, null);
    }

    public TaskRouterCapability addPolicy(final Policy policy) {
        if (!checkPolicy(policy)) {
            this.policies.add(policy);
            return this;
        } else {
            throw new RuntimeException("Policy already exists");
        }

    }

    @Override
    public void addPolicy(final String url, final String method, final Map<String, FilterRequirement> queryFilter, final Map<String, FilterRequirement> postFilter, final boolean allow) {
        final Policy policy = new Policy(url, method, queryFilter, postFilter, allow);
        if (!checkPolicy(policy)) {
            this.policies.add(policy);
        } else {
            throw new RuntimeException("Policy already exists");
        }
    }

    @Override
    public void allow(final String url, final String method, final Map<String, FilterRequirement> queryFilter, final Map<String, FilterRequirement> postFilter) {
        final Policy policy = new Policy(url, method, queryFilter, postFilter, true);
        if (!checkPolicy(policy)) {
            this.policies.add(policy);
        } else {
            throw new RuntimeException("Policy already exists");
        }
    }

    @Override
    public void disAllow(final String url, final String method, final Map<String, FilterRequirement> queryFilter, final Map<String, FilterRequirement> postFilter) {
        final Policy policy = new Policy(url, method, queryFilter, postFilter, false);
        if (!checkPolicy(policy)) {
            this.policies.add(policy);
        } else {
            throw new RuntimeException("Policy already exists");
        }
    }

    public String getResourceUrl() {
        return this.resourceUrl;
    }

    public String generateToken() throws DomainException {
        return generateToken(3600);
    }

    public String generateToken(final long ttl) throws DomainException {

        final Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("iss", accountSid);
        payload.put("exp", (System.currentTimeMillis() / 1000L) + ttl);
        payload.put("account_sid", accountSid);
        payload.put("friendly_name", friendlyName);
        payload.put("version", version);
        payload.put("policies", policies);
        payload.put("workspace_sid", this.workspaceSid);
        payload.put("channel", this.channelId);

        if (channelId.substring(0, 2).equals("WK")) {
            payload.put("worker_sid", this.channelId);
        } else if (channelId.substring(0, 2).equals("WQ")) {
            payload.put("taskqueue_sid", this.channelId);
        }

        try {
            return jwtEncode(payload, authToken);
        } catch (final Exception e) {
            throw new DomainException(e);
        }

    }

    private boolean checkPolicy(final Policy policy) {
        if (this.policies.contains(policy)) {
            return true;
        }
        return false;
    }
}
