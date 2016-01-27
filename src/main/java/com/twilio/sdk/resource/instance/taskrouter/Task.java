package com.twilio.sdk.resource.instance.taskrouter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenInstanceResource;
import com.twilio.sdk.resource.list.taskrouter.ReservationList;

/**
 * A Task resource represents a single item of work waiting to be processed.
 *
 * See <a href="https://www.twilio.com/docs/taskrouter/tasks">the TaskRouter documentation</a>.
 */
public class Task extends NextGenInstanceResource<TwilioTaskRouterClient> {

	private static final String WORKSPACE_SID_PROPERTY = "workspace_sid";
	
	private static JSONParser parser = new JSONParser();

	/**
	 * Instantiates a task.
	 *
	 * @param client the client
	 */
	public Task(final TwilioTaskRouterClient client) {
		super(client);
	}

	/**
	 * Instantiates a task.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Task(final TwilioTaskRouterClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * Instantiates a task.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param taskSid the task sid
	 */
	public Task(final TwilioTaskRouterClient client, final String workspaceSid, final String taskSid) {
		super(client);
		if (StringUtils.isBlank(workspaceSid)) {
			throw new IllegalArgumentException("The workspaceSid for an Task cannot be null");
		}
		if (StringUtils.isBlank(taskSid)) {
			throw new IllegalArgumentException("The taskSid for an Task cannot be null");
		}
		setProperty(WORKSPACE_SID_PROPERTY, workspaceSid);
		setProperty(SID_PROPERTY, taskSid);
	}
	
	/**
	 * Update a task with new attributes and/or priority
	 * @param attributes new attributes for the task
	 * @param priority new priority of the task
	 * @throws TwilioRestException
	 */
	public void update(final Map<String, String> attributes, final Integer priority) throws TwilioRestException {
		Map<String, String> params = new HashMap<String, String>();
		if(attributes != null) {
			params.put("Attributes", JSONObject.toJSONString(attributes));
		}else {
			params.put("Attributes", "{}");
		}
		if(priority != null) {
			params.put("Priority", priority.toString());
		}
		this.update(params);
	}
	
	/**
	 * Cancel a task with an optional reason
	 * @param reason optional reason for cancellation
	 * @throws TwilioRestException
	 */
	public void cancel(final String reason) throws TwilioRestException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("AssignmentStatus", "canceled");
		if(reason != null) {
			params.put("Reason", reason);
		}
		this.update(params);
	}

	/**
	 * The ID of the {@link com.twilio.sdk.resource.instance.Account} that owns this Task.
	 *
	 * @return the account sid
	 */
	public String getAccountSid() {
		return getProperty("account_sid");
	}

	/**
	 * The number of seconds since this Task was created.
	 *
	 * @return the age
	 */
	public int getAge() {
		Integer prop = (Integer) getObject("age");
		if (prop != null) {
			return prop;
		} else {
			throw new IllegalStateException("The Task doesn't have its age set");
		}
	}

	/**
	 * A string representing the Assignment State of the task. May be "pending", "reserved", "assigned" or "canceled".
	 *
	 * @return the assignment status
	 */
	public String getAssignmentStatus() {
		return getProperty("assignment_status");
	}

	/**
	 * A user-defined JSON object describing this Task.
	 *
	 * @return the attributes
	 */
	public String getAttributes() {
		return getProperty("attributes");
	}
	
	/**
	 * A map that represents the JSON describing this Task.
	 *
	 * @return the attributes
	 * @throws ParseException 
	 */
	public Map<String, Object> parseAttributes() throws ParseException {
		String attributes = getProperty("attributes");
		return (Map<String, Object>) parser.parse(attributes);
	}

	/**
	 * Date and time this Task was created.
	 *
	 * @return the date created
	 */
	public Date getDateCreated() {
		return parseIsoDate(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * Date and time this Task was last updated.
	 *
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		return parseIsoDate(getProperty(DATE_UPDATED_PROPERTY));
	}

	/**
	 * The current priority score of the task, as assigned by the workflow. Tasks with higher values will be assigned before tasks with lower values.
	 *
	 * @return the priority
	 */
	public int getPriority() {
		Integer prop = (Integer) getObject("priority");
		if (prop != null) {
			return prop;
		} else {
			throw new IllegalStateException("The Task doesn't have its priority set");
		}
	}

	/**
	 * The Task assignment request timeout, in seconds.
	 *
	 * @return the timeout
	 */
	public int getTimeout() {
		return (Integer)getObject("timeout");
	}

	/**
	 * The unique ID of the {@link com.twilio.sdk.resource.instance.taskrouter.TaskQueue} this Task occupies.
	 *
	 * @return the {@link TaskQueue} sid
	 */
	public String getQueueSid() {
		return getProperty("task_queue_sid");
	}

	/**
	 * The unique ID of this Task.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * The ID of the {@link com.twilio.sdk.resource.instance.taskrouter.Workflow} responsible for routing this Task.
	 *
	 * @return the workflow sid
	 */
	public String getWorkflowSid() {
		return getProperty("workflow_sid");
	}

	/**
	 * The ID of the {@link com.twilio.sdk.resource.instance.taskrouter.Workspace} containing this Task.
	 *
	 * @return the workspace sid
	 */
	public String getWorkspaceSid() {
		return getProperty(WORKSPACE_SID_PROPERTY);
	}

	/**
	 * The reason the Task was canceled (if applicable).
	 *
	 * @return cancellation reason
	 */
	public String getReason() {
		return getProperty("reason");
	}

	/**
	 * Retrieve the {@link com.twilio.sdk.resource.list.taskrouter.ReservationList} for this Task.
	 * @return
	 */
	public ReservationList getReservations() {
		ReservationList list = new ReservationList(this.getClient(), this.getWorkspaceSid(), this.getSid());
		return list;
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + getWorkspaceSid() + "/Tasks/" + getSid();
	}
}
