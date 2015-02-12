package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.InstanceResource;
import com.twilio.sdk.resource.list.taskrouter.ActivityList;
import com.twilio.sdk.resource.list.taskrouter.TaskList;
import com.twilio.sdk.resource.list.taskrouter.WorkerList;
import com.twilio.sdk.resource.list.taskrouter.WorkflowList;

import java.util.Date;
import java.util.Map;

/**
 * Workspaces are containers that bring all of these pieces together. All of your Tasks, Workers, Workflows and Queues
 * are contained within a workspace. You can have as many workspaces as you like.
 */
public class Workspace extends InstanceResource<TwilioTaskRouterClient> {

	/**
	 * Instantiates a workspace.
	 *
	 * @param client the client
	 */
	public Workspace(final TwilioTaskRouterClient client) {
		super(client);
	}

	/**
	 * Instantiates a workspace.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Workspace(final TwilioTaskRouterClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * Instantiates a workspace.
	 *
	 * @param client the client
	 * @param sid the sid
	 */
	public Workspace(final TwilioTaskRouterClient client, final String sid) {
		super(client);
		if (sid == null || "".equals(sid)) {
			throw new IllegalArgumentException("The Sid for a Workspace cannot be null");
		}
		setProperty(SID_PROPERTY, sid);
	}

	/**
	 * Gets the account sid.
	 *
	 * @return the account sid
	 */
	public String getAccountSid() {
		return getProperty("account_sid");
	}

	/**
	 * Retrieves the {@link com.twilio.sdk.resource.list.taskrouter.ActivityList} for this {@link
	 * com.twilio.sdk.resource.instance.taskrouter.Workspace}
	 *
	 * @return the {@link com.twilio.sdk.resource.list.taskrouter.ActivityList}
	 */
	public ActivityList getActivities() {
		ActivityList activities = new ActivityList(getClient(), getSid());
		activities.setRequestAccountSid(getRequestAccountSid());
		return activities;
	}

	/**
	 * Retrieves an Activity from a Workspace
	 */
	public Activity getActivity(final String activitySid) {
		Activity activity = new Activity(getClient(), getSid(), activitySid);
		activity.setRequestAccountSid(getRequestAccountSid());
		return activity;
	}

	/**
	 * Gets the date created.
	 *
	 * @return the date created
	 */
	public Date getDateCreated() {
		return parseDate(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * Gets the date updated.
	 *
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		return parseDate(getProperty(DATE_UPDATED_PROPERTY));
	}

	/**
	 * Gets the default activity name.
	 *
	 * @return the default activity name
	 */
	public String getDefaultActivityName() {
		return getProperty("default_activity_name");
	}

	/**
	 * Gets the default activity sid.
	 *
	 * @return the default activity sid
	 */
	public String getDefaultActivitySid() {
		return getProperty("default_activity_sid");
	}

	/**
	 * Gets the event callback URL.
	 *
	 * @return the event callback URL
	 */
	public String getEventCallbackUrl() {
		return getProperty("event_callback_url");
	}

	/**
	 * Gets the friendly name
	 *
	 * @return the friendly name
	 */
	public String getFriendlyName() {
		return getProperty("friendly_name");
	}

	/**
	 * Gets the sid.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * Retrieves a Task from a Workspace
	 */
	public Task getTask(final String taskSid) {
		Task task = new Task(getClient(), getSid(), taskSid);
		task.setRequestAccountSid(getRequestAccountSid());
		return task;
	}

	/**
	 * Retrieves the {@link com.twilio.sdk.resource.list.taskrouter.TaskList} for this {@link
	 * com.twilio.sdk.resource.instance.taskrouter.Workspace}
	 *
	 * @return the {@link com.twilio.sdk.resource.list.taskrouter.TaskList}
	 */
	public TaskList getTasks() {
		TaskList tasks = new TaskList(getClient(), getSid());
		tasks.setRequestAccountSid(getRequestAccountSid());
		return tasks;
	}

	/**
	 * Gets the timeout activity name.
	 *
	 * @return the timeout activity name
	 */
	public String getTimeoutActivityName() {
		return getProperty("timeout_activity_name");
	}

	/**
	 * Gets the timeout activity sid.
	 *
	 * @return the timeout activity sid
	 */
	public String getTimeoutActivitySid() {
		return getProperty("timeout_activity_sid");
	}

	/**
	 * Retrieves a {@link com.twilio.sdk.resource.instance.taskrouter.Worker} from a Workspace
	 */
	public Worker getWorker(final String workerSid) {
		Worker worker = new Worker(getClient(), getSid(), workerSid);
		worker.setRequestAccountSid(getRequestAccountSid());
		return worker;
	}

	/**
	 * Retrieves the {@link com.twilio.sdk.resource.list.taskrouter.WorkerList} for this {@link
	 * com.twilio.sdk.resource.instance.taskrouter.Workspace}
	 *
	 * @return the {@link com.twilio.sdk.resource.list.taskrouter.WorkerList}
	 */
	public WorkerList getWorkers() {
		WorkerList workers = new WorkerList(getClient(), getSid());
		workers.setRequestAccountSid(getRequestAccountSid());
		return workers;
	}

	/**
	 * Retrieves a {@link com.twilio.sdk.resource.instance.taskrouter.Workflow} from a Workspace
	 */
	public Workflow getWorkflow(final String workflowSid) {
		Workflow workflow = new Workflow(getClient(), getSid(), workflowSid);
		workflow.setRequestAccountSid(getRequestAccountSid());
		return workflow;
	}

	/**
	 * Retrieves the {@link com.twilio.sdk.resource.list.taskrouter.WorkflowList} for this {@link
	 * com.twilio.sdk.resource.instance.taskrouter.Workspace}
	 *
	 * @return the {@link com.twilio.sdk.resource.list.taskrouter.WorkflowList}
	 */
	public WorkflowList getWorkflows() {
		WorkflowList workflows = new WorkflowList(getClient(), getSid());
		workflows.setRequestAccountSid(getRequestAccountSid());
		return workflows;
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" +
		       getSid();
	}
}
