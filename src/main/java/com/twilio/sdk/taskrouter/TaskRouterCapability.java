package com.twilio.sdk.taskrouter;

import java.util.HashMap;
import java.util.Map;

public class TaskRouterCapability extends CapabilityAPI {

    private final static String TASKROUTER_BASE_URL = "https://taskrouter.twilio.com";
    private final static String TASKROUTER_VERSION = "v1";
    private final static String TASKROUTER_EVENT_URL = "https://event-bridge.twilio.com/v1/wschannels";

    protected String workspaceSid;
    protected String channelId;
    protected String resourceUrl;
    protected String baseUrl;

	/**
	 * Create a new Capability object to authorize clients to interact with the
	 * TaskRouter service.
	 *
	 * @param accountSid   Account to authorize actions for
	 * @param authToken    Auth token for the account. Used to sign tokens and will not be
	 *                     included in the generated tokens.
	 * @param workspaceSid Workspace to authorize tokens for.
	 * @param channelId    Authorized Channel
	 */
    public TaskRouterCapability(final String accountSid, final String authToken, final String workspaceSid, final String channelId) throws Exception {
        super(accountSid, authToken, TASKROUTER_VERSION, channelId);
        this.workspaceSid = workspaceSid;
        this.channelId = channelId;
        this.baseUrl = TASKROUTER_BASE_URL + "/" + TASKROUTER_VERSION + "/Workspaces/" + workspaceSid;
        
        validateJWT();
        
        this.setupResource();
        
        // add permissions to GET and POST to the event-bridge channel
        addTaskRouterPolicies(channelId);
        this.addPolicy(new Policy(resourceUrl, "GET", true));
    }
    
    public void setupResource() {
    	if (channelId.substring(0, 2).equals("WS")) {
            resourceUrl = this.baseUrl;
        } else if (channelId.substring(0, 2).equals("WK")) {
            resourceUrl = this.baseUrl + "/Workers/" + channelId;
            
            String activityUrl = this.baseUrl + "/Activities";

            // add permissions to fetch the list of activities
            this.allow(activityUrl, "GET", null, null);
        } else if (channelId.substring(0, 2).equals("WQ")) {
            resourceUrl = this.baseUrl + "/TaskQueues/" + channelId;
        }
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

    /**
     * Allow fetching all subresources of the defined resource
     */
    public void allowFetchSubresources() {
        this.allow(this.resourceUrl + "/**", "GET", null, null);
    }

    /**
     * Allow updating attributes of the defined resource
     */
    public void allowUpdates() {
        this.allow(this.resourceUrl, "POST", null, null);
    }

    /**
     * Allow updating attributes of all subresources of the defined resource
     */
    public void allowUpdatesSubresources() {
        this.allow(this.resourceUrl + "/**", "POST", null, null);
    }

    /**
     * Allow deletion of the defined resource
     */
    public void allowDelete() {
        this.allow(this.resourceUrl, "DELETE", null, null);
    }

    /**
     * Allow deletion of any subresources of the defined resource
     */
    public void allowDeleteSubresources() {
        this.allow(this.resourceUrl + "/**", "DELETE", null, null);
    }
    
    /**
	 * Allow a worker to update its own activity status
	 * @deprecated Please use {TaskRouterWorkerCapability.allowActivityUpdates} instead
     * @throws Exception 
	 */
    @Deprecated
	public void allowWorkerActivityUpdates() throws Exception {
    	if (channelId.substring(0, 2).equals("WK")) {
    		Policy update = new Policy(this.resourceUrl, "POST", true).addPostFilterParam("ActivitySid", FilterRequirement.REQUIRED);
    		this.addPolicy(update);
    	}else {
    		throw new Exception("Deprecated function not applicable to non Worker");
    	}
	}

	/**
	 * Allow a worker to read its own attributes.
	 * @deprecated Please use {TaskRouterWorkerCapability} instead; added automatically in constructor
	 * @throws Exception 
	 */
    @Deprecated
	public void allowWorkerFetchAttributes() throws Exception {
		if (channelId.substring(0, 2).equals("WK")) {
			this.addPolicy(new Policy(this.resourceUrl, "GET", true));
		} else {
			throw new Exception("Deprecated function not applicable to non Worker");
		}
	}

	/**
	 * Allow a worker to update task reservation status
	 * @deprecated Please use {TaskRouterWorkerCapability.allowReservationUpdates} instead
	 * @throws Exception 
	 */
    @Deprecated
	public void allowTaskReservationUpdates() throws Exception {
    	if (channelId.substring(0, 2).equals("WK")) {
    		String tasksUrl = this.baseUrl + "/Tasks/**";
    		Policy update = new Policy(tasksUrl, "POST", true);
    		this.addPolicy(update);
    	}else {
    		throw new Exception("Deprecated function not applicable to non Worker");
    	}

	}
    
    public TaskRouterCapability addPolicy(final Policy policy) {
        if (!checkPolicy(policy)) {
            this.policies.add(policy);
            return this;
        } else {
            throw new RuntimeException("Policy already exists");
        }
    }

    /**
     * Add policy to list of access policies
     * @param url url of the resource
     * @param method method to the resource
     * @param queryFilter query filter parameters
     * @param postFilter post filter parameters
     * @param allow whether or not to allow access on this policy
     */
    @Override
    public void addPolicy(final String url, final String method, final Map<String, FilterRequirement> queryFilter, final Map<String, FilterRequirement> postFilter, final boolean allow) {
        final Policy policy = new Policy(url, method, queryFilter, postFilter, allow);
        if (!checkPolicy(policy)) {
            this.policies.add(policy);
        } else {
            throw new RuntimeException("Policy already exists");
        }
    }

    /**
     * Add Allow access policy
     * @param url url of the resource
     * @param method method to the resource
     * @param queryFilter query filter parameters
     * @param postFilter post filter parameters
     */
    @Override
    public void allow(final String url, final String method, final Map<String, FilterRequirement> queryFilter, final Map<String, FilterRequirement> postFilter) {
        final Policy policy = new Policy(url, method, queryFilter, postFilter, true);
        if (!checkPolicy(policy)) {
            this.policies.add(policy);
        } else {
            throw new RuntimeException("Policy already exists");
        }
    }

    /**
     * Add Deny access policy
     * @param url url of the resource
     * @param method method to the resource
     * @param queryFilter query filter parameters
     * @param postFilter post filter parameters
     */
    @Override
    public void deny(final String url, final String method, final Map<String, FilterRequirement> queryFilter, final Map<String, FilterRequirement> postFilter) {
        final Policy policy = new Policy(url, method, queryFilter, postFilter, false);
        if (!checkPolicy(policy)) {
            this.policies.add(policy);
        } else {
            throw new RuntimeException("Policy already exists");
        }
    }

    /**
	 * Generate a capability token with the currently-configured policies on this object.
	 *
	 * @return JSON Web Token representing authorized capabilities
	 * @throws DomainException
	 */
    public String generateToken() throws DomainException {
        return generateToken(3600);
    }

    /**
	 * Generate a capability token with the currently-configured policies on this object.
	 *
	 * @param ttl Expiration time in seconds
	 * @return JSON Web Token representing authorized capabilities
	 * @throws DomainException
	 */
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
