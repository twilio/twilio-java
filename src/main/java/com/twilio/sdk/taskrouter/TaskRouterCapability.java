package com.twilio.sdk.taskrouter;

import com.twilio.sdk.CapabilityToken;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskRouterCapability extends CapabilityToken {

	private final static String TASKROUTER_BASE_URL = "https://taskrouter.twilio.com";
	private final static String TASKROUTER_VERSION = "v1";
	private final static String TASKROUTER_EVENT_URL = "https://event-bridge.twilio.com/v1/wschannels";

	private String accountSid;
	private String authToken;
	private String workspaceSid;
	private String workerSid;
	private List<Policy> policies;

	/**
	 * Create a new Capability object to authorize worker clients to interact with the
	 * TaskRouter service.
	 *
	 * @param accountSid   Account to authorize actions for
	 * @param authToken    Auth token for the account. Used to sign tokens and will not be
	 *                     included in the generated tokens.
	 * @param workspaceSid Workspace to authorize tokens for.
	 * @param workerSid    Worker to create tokens for.
	 */
	public TaskRouterCapability(final String accountSid, final String authToken, final String workspaceSid, final String workerSid) {
		this.accountSid = accountSid;
		this.authToken = authToken;
		this.workspaceSid = workspaceSid;
		this.workerSid = workerSid;
		this.policies = new ArrayList<Policy>();
		addEventBridgePolicies();
	}

	/**
	 * Allow a worker to update its own activity status
	 *
	 * @return The updated Capability representation
	 */
	public TaskRouterCapability allowWorkerActivityUpdates() {
		Policy update = new Policy(getWorkerUrl(), "POST", true).addPostFilterParam("ActivitySid", FilterRequirement.REQUIRED);
		return addPolicy(update);
	}

	/**
	 * Allow a worker to read its own attributes.
	 *
	 * @return The updated Capability representation
	 */
	public TaskRouterCapability allowWorkerFetchAttributes() {
		return addPolicy(new Policy(getWorkerUrl(), "GET", true));
	}

	/**
	 * Allow a worker to update task reservation status
	 *
	 * @return The updated Capability representation
	 */
	public TaskRouterCapability allowTaskReservationUpdates() {
		String tasksUrl = getWorkspaceUrl() + "/Tasks/**";
		Policy update = new Policy(tasksUrl, "POST", true).addPostFilterParam("ReservationStatus", FilterRequirement.REQUIRED);
		return addPolicy(update);
	}

	/**
	 * Add a new Policy allowing or denying specific resource actions to this Capability set.
	 *
	 * @param policy Configured Policy object
	 * @return The updated Capability representation
	 */
	public TaskRouterCapability addPolicy(final Policy policy) {
		this.policies.add(policy);
		return this;
	}

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
	public String generateToken(long ttl) throws DomainException {
		Map<String, Object> payload = new HashMap<String, Object>();
		payload.put("iss", accountSid);
		payload.put("exp", (System.currentTimeMillis() / 1000L) + ttl);
		payload.put("version", TASKROUTER_VERSION);
		payload.put("account_sid", accountSid);
		payload.put("worker_sid", workerSid);
		payload.put("workspace_sid", workspaceSid);
		payload.put("channel", workerSid);
		payload.put("friendly_name", workerSid);
		payload.put("policies", policies);

		try {
			return jwtEncode(payload, authToken);
		} catch (Exception e) {
			throw new DomainException(e);
		}
	}

	private String getWorkspaceUrl() {
		return TASKROUTER_BASE_URL + "/" + TASKROUTER_VERSION + "/Workspaces/" + workspaceSid;
	}

	private String getWorkerUrl() {
		return getWorkspaceUrl() + "/Workers/" + workerSid;
	}

	private void addEventBridgePolicies() {
		// Workers can GET and POST their own events
		String eventBridgeUrl = TASKROUTER_EVENT_URL + "/" + accountSid + "/" + workerSid;
		addPolicy(new Policy(eventBridgeUrl, "GET", true));
		addPolicy(new Policy(eventBridgeUrl, "POST", true));
	}

	public class Policy implements JSONAware {
		private String url;
		private String method;
		private Map<String, FilterRequirement> queryFilter;
		private Map<String, FilterRequirement> postFilter;
		private boolean allowed;

		/**
		 * Represents permissions for a specific operation against a TaskRouter resource.
		 *
		 * @param url         The URL of the resource to grant or deny permissions to
		 * @param method      The HTTP method
		 * @param queryFilter Allowed or required parameters for GET requests
		 * @param postFilter  Allowed or required parameters for POST requests
		 * @param allowed     Whether this action is allowed or not
		 */
		public Policy(final String url, final String method, final Map<String, FilterRequirement> queryFilter, final Map<String, FilterRequirement> postFilter, final boolean allowed) {
			this.url = url;
			this.method = method;
			this.queryFilter = queryFilter;
			this.postFilter = postFilter;
			this.allowed = allowed;
		}

		public Policy(final String url, final String method, final boolean allowed) {
			this.url = url;
			this.method = method;
			this.allowed = allowed;
			setQueryFilter(new HashMap<String, FilterRequirement>());
			setPostFilter(new HashMap<String, FilterRequirement>());
		}

		public Policy addQueryFilterParam(final String name, final FilterRequirement required) {
			queryFilter.put(name, required);
			return this;
		}

		public Policy addPostFilterParam(final String name, final FilterRequirement required) {
			postFilter.put(name, required);
			return this;
		}

		public Policy setQueryFilter(final Map<String, FilterRequirement> queryFilter) {
			this.queryFilter = queryFilter;
			return this;
		}

		public Policy setPostFilter(final Map<String, FilterRequirement> postFilter) {
			this.postFilter = postFilter;
			return this;
		}

		@Override
		public String toJSONString() {
			JSONObject obj = new JSONObject();
			obj.put("url", url);
			obj.put("method", method);
			obj.put("allow", allowed);
			JSONObject query = new JSONObject();
			JSONObject post = new JSONObject();

			for (Map.Entry<String, FilterRequirement> e : queryFilter.entrySet()) {
				query.put(e.getKey(), e.getValue().toString());
			}
			for (Map.Entry<String, FilterRequirement> e : postFilter.entrySet()) {
				post.put(e.getKey(), e.getValue().toString());
			}

			obj.put("query_filter", query);
			obj.put("post_filter", post);
			return obj.toJSONString();
		}
	}

}
