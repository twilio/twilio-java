package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenInstanceResource;
import com.twilio.sdk.resource.list.taskrouter.ActivityList;
import com.twilio.sdk.resource.list.taskrouter.EventList;
import com.twilio.sdk.resource.list.taskrouter.TaskList;
import com.twilio.sdk.resource.list.taskrouter.WorkerList;
import com.twilio.sdk.resource.list.taskrouter.WorkflowList;

import java.util.Date;
import java.util.Map;

/**
 * A Workspace is a container for your Tasks, Workers, TaskQueues, Workflows and Activities. Each of these items exists
 * within a single Workspace and will not be shared across Workspaces.
 */
public class Workspace extends NextGenInstanceResource<TwilioTaskRouterClient> {

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
	 * The ID of the {@link com.twilio.sdk.resource.instance.Account} that owns this Workspace.
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
		return activities;
	}

	/**
	 * Retrieves an {@link com.twilio.sdk.resource.instance.taskrouter.Activity} from this Workspace by ID.
	 */
	public Activity getActivity(final String activitySid) {
		Activity activity = new Activity(getClient(), getSid(), activitySid);
		return activity;
	}

	/**
	 * The date and time this Workspace was created.
	 *
	 * @return the date created
	 */
	public Date getDateCreated() {
		return parseDate(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * The date and time this Workspace was last updated.
	 *
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		return parseDate(getProperty(DATE_UPDATED_PROPERTY));
	}

	/**
	 * The human-readable name of the default activity. Read only.
	 *
	 * @return the default activity name
	 */
	public String getDefaultActivityName() {
		return getProperty("default_activity_name");
	}

	/**
	 * The ID of the Activity that will be used when new Workers are created in this Workspace.
	 *
	 * @return the default activity sid
	 */
	public String getDefaultActivitySid() {
		return getProperty("default_activity_sid");
	}

	/**
	 * Retrieves an {@link com.twilio.sdk.resource.instance.taskrouter.Event} from this Workspace
	 */
	public Event getEvent(final String eventSid) {
		Event event = new Event(getClient(), getSid(), eventSid);
		return event;
	}

	/**
	 * Retrieves the {@link com.twilio.sdk.resource.list.taskrouter.EventList} for this {@link
	 * com.twilio.sdk.resource.instance.taskrouter.Workspace}
	 *
	 * @return the {@link com.twilio.sdk.resource.list.taskrouter.EventList}
	 */
	public EventList getEvents() {
		EventList events = new EventList(getClient(), getSid());
		return events;
	}

	/**
	 * An optional URL where the Workspace will publish events. You can use this to gather data for reporting.
	 *
	 * @return the event callback URL
	 */
	public String getEventCallbackUrl() {
		return getProperty("event_callback_url");
	}

	/**
	 * A human-readable description of this Workspace.
	 *
	 * @return the friendly name
	 */
	public String getFriendlyName() {
		return getProperty("friendly_name");
	}

	/**
	 * The unique ID for this Workspace.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * Retrieves a {@link com.twilio.sdk.resource.instance.taskrouter.Task} from this Workspace.
	 */
	public Task getTask(final String taskSid) {
		Task task = new Task(getClient(), getSid(), taskSid);
		return task;
	}

	/**
	 * Retrieves the {@link com.twilio.sdk.resource.list.taskrouter.TaskList} for this {@link
	 * com.twilio.sdk.resource.instance.taskrouter.Workspace}.
	 *
	 * @return the {@link com.twilio.sdk.resource.list.taskrouter.TaskList}
	 */
	public TaskList getTasks() {
		TaskList tasks = new TaskList(getClient(), getSid());
		return tasks;
	}

	/**
	 * The human-readable name of the timeout activity. Read only.
	 *
	 * @return the timeout activity name
	 */
	public String getTimeoutActivityName() {
		return getProperty("timeout_activity_name");
	}

	/**
	 * The ID of the {@link com.twilio.sdk.resource.instance.taskrouter.Activity} that will be assigned to a
	 * {@link com.twilio.sdk.resource.instance.taskrouter.Worker} when a
	 * {@link com.twilio.sdk.resource.instance.taskrouter.Task} reservation times out without a response.
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
		return workers;
	}

	/**
	 * Retrieves a {@link com.twilio.sdk.resource.instance.taskrouter.Workflow} from a Workspace
	 */
	public Workflow getWorkflow(final String workflowSid) {
		Workflow workflow = new Workflow(getClient(), getSid(), workflowSid);
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
		return workflows;
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + getSid();
	}
}
