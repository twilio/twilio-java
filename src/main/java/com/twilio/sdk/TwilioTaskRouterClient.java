package com.twilio.sdk;

import com.twilio.sdk.resource.factory.taskrouter.ActivityFactory;
import com.twilio.sdk.resource.factory.taskrouter.TaskFactory;
import com.twilio.sdk.resource.factory.taskrouter.TaskQueueFactory;
import com.twilio.sdk.resource.factory.taskrouter.WorkerFactory;
import com.twilio.sdk.resource.factory.taskrouter.WorkflowFactory;
import com.twilio.sdk.resource.factory.taskrouter.WorkspaceFactory;
import com.twilio.sdk.resource.instance.taskrouter.Activity;
import com.twilio.sdk.resource.instance.taskrouter.Event;
import com.twilio.sdk.resource.instance.taskrouter.Reservation;
import com.twilio.sdk.resource.instance.taskrouter.Task;
import com.twilio.sdk.resource.instance.taskrouter.TaskQueue;
import com.twilio.sdk.resource.instance.taskrouter.TaskQueueStatistics;
import com.twilio.sdk.resource.instance.taskrouter.Worker;
import com.twilio.sdk.resource.instance.taskrouter.WorkerStatistics;
import com.twilio.sdk.resource.instance.taskrouter.WorkersStatistics;
import com.twilio.sdk.resource.instance.taskrouter.Workflow;
import com.twilio.sdk.resource.instance.taskrouter.WorkflowStatistics;
import com.twilio.sdk.resource.instance.taskrouter.Workspace;
import com.twilio.sdk.resource.instance.taskrouter.WorkspaceStatistics;
import com.twilio.sdk.resource.list.taskrouter.ActivityList;
import com.twilio.sdk.resource.list.taskrouter.EventList;
import com.twilio.sdk.resource.list.taskrouter.ReservationList;
import com.twilio.sdk.resource.list.taskrouter.TaskList;
import com.twilio.sdk.resource.list.taskrouter.TaskQueueList;
import com.twilio.sdk.resource.list.taskrouter.TaskQueueListStatistics;
import com.twilio.sdk.resource.list.taskrouter.WorkerList;
import com.twilio.sdk.resource.list.taskrouter.WorkflowList;
import com.twilio.sdk.resource.list.taskrouter.WorkspaceList;

import java.util.HashMap;
import java.util.Map;

/**
 * The client class that access http://taskrouter.twilio.com.
 */
public class TwilioTaskRouterClient extends TwilioClient {

	public static final String DEFAULT_VERSION = "v1";

	public TwilioTaskRouterClient(final String accountSid, final String authToken) {
		super(accountSid, authToken, "https://taskrouter.twilio.com");
	}
	
	public TwilioTaskRouterClient(final String accountSid, final String authToken, final String endpoint) {
		super(accountSid, authToken, endpoint);
	}

	/**
	 * Create an {@link com.twilio.sdk.resource.instance.taskrouter.Activity}.
	 *
	 * @param properties activity properties
	 * @return created activity
	 * @throws TwilioRestException
	 */
	public Activity createActivity(final String workspaceSid, final Map<String, String> properties) throws
	                                                                                                TwilioRestException {
		ActivityFactory activityFactory = new ActivityList(this, workspaceSid);
		return activityFactory.create(properties);
	}

	/**
	 * Create an {@link com.twilio.sdk.resource.instance.taskrouter.TaskQueue}.
	 *
	 * @param properties queue properties
	 * @return created queue
	 * @throws TwilioRestException
	 */
	public TaskQueue createTaskQueue(final String workspaceSid, final Map<String, String> properties) throws
	                                                                                                  TwilioRestException {
		TaskQueueFactory taskQueueFactory = new TaskQueueList(this, workspaceSid);
		return taskQueueFactory.create(properties);
	}

	/**
	 * Create an {@link com.twilio.sdk.resource.instance.taskrouter.Task}.
	 *
	 * @param properties task properties
	 * @return created task
	 * @throws TwilioRestException
	 */
	public Task createTask(final String workspaceSid, final Map<String, String> properties) throws TwilioRestException {
		TaskFactory taskFactory = new TaskList(this, workspaceSid);
		return taskFactory.create(properties);
	}

	/**
	 * Create an {@link com.twilio.sdk.resource.instance.taskrouter.Worker}.
	 *
	 * @param properties task properties
	 * @return created worker
	 * @throws TwilioRestException
	 */
	public Worker createWorker(final String workspaceSid, final Map<String, String> properties) throws
	                                                                                            TwilioRestException {
		WorkerFactory factory = new WorkerList(this, workspaceSid);
		return factory.create(properties);
	}

	/**
	 * Create an {@link com.twilio.sdk.resource.instance.taskrouter.Workflow}.
	 *
	 * @param properties task properties
	 * @return created workflow
	 * @throws TwilioRestException
	 */
	public Workflow createWorkflow(final String workspaceSid, final Map<String, String> properties) throws
	                                                                                                TwilioRestException {
		WorkflowFactory factory = new WorkflowList(this, workspaceSid);
		return factory.create(properties);
	}

	/**
	 * Create a {@link com.twilio.sdk.resource.instance.taskrouter.Workspace}.
	 *
	 * @param properties workspace properties
	 * @return created workspace
	 * @throws TwilioRestException
	 */
	public Workspace createWorkspace(final Map<String, String> properties) throws TwilioRestException {
		WorkspaceFactory workspaceFactory = new WorkspaceList(this);
		return workspaceFactory.create(properties);
	}

	/**
	 * Deletes an activity.
	 *
	 * @param workspaceSid the workspace sid
	 * @param activitySid the activity sid
	 * @return true, if successful
	 * @throws TwilioRestException the twilio rest exception
	 */
	public boolean deleteActivity(final String workspaceSid, final String activitySid) throws TwilioRestException {
		TwilioRestResponse response = safeRequest(
				"/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + workspaceSid + "/Activities/" +
				activitySid, "DELETE", (Map) null);

		return !response.isError();
	}

	/**
	 * Deletes a queue.
	 *
	 * @param workspaceSid the workspace sid
	 * @param queueSid the queue sid
	 * @return true, if successful
	 * @throws TwilioRestException the twilio rest exception
	 */
	public boolean deleteTaskQueue(final String workspaceSid, final String queueSid) throws TwilioRestException {
		TwilioRestResponse response = safeRequest(
				"/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + workspaceSid + "/TaskQueues/" +
				queueSid, "DELETE", (Map) null);

		return !response.isError();
	}

	/**
	 * Deletes a task.
	 *
	 * @param workspaceSid the workspace sid
	 * @param taskSid the task sid
	 * @return true, if successful
	 * @throws TwilioRestException the twilio rest exception
	 */
	public boolean deleteTask(final String workspaceSid, final String taskSid) throws TwilioRestException {
		TwilioRestResponse response = safeRequest(
				"/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + workspaceSid + "/Tasks/" + taskSid,
				"DELETE", (Map) null);

		return !response.isError();
	}

	/**
	 * Deletes a worker.
	 *
	 * @param workspaceSid the workspace sid
	 * @param workerSid the worker sid
	 * @return true, if successful
	 * @throws TwilioRestException the twilio rest exception
	 */
	public boolean deleteWorker(final String workspaceSid, final String workerSid) throws TwilioRestException {
		TwilioRestResponse response = safeRequest(
				"/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + workspaceSid + "/Workers/" + workerSid,
				"DELETE", (Map) null);

		return !response.isError();
	}

	/**
	 * Deletes a workflow.
	 *
	 * @param workspaceSid the workspace sid
	 * @param workflowSid the workflow sid
	 * @return true, if successful
	 * @throws TwilioRestException the twilio rest exception
	 */
	public boolean deleteWorkflow(final String workspaceSid, final String workflowSid) throws TwilioRestException {
		TwilioRestResponse response = safeRequest(
				"/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + workspaceSid + "/Workflows/" +
				workflowSid, "DELETE", (Map) null);

		return !response.isError();
	}

	/**
	 * Deletes a workspace.
	 *
	 * @param sid the workspace sid
	 * @return true, if successful
	 * @throws TwilioRestException the twilio rest exception
	 */
	public boolean deleteWorkspace(final String sid) throws TwilioRestException {
		TwilioRestResponse response = safeRequest("/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + sid,
		                                          "DELETE", (Map) null);

		return !response.isError();
	}

	/**
	 * Get the activities.
	 *
	 * @return the activities
	 */
	public ActivityList getActivities(final String workspaceSid) {
		return getActivities(workspaceSid, new HashMap<String, String>(0));
	}

	/**
	 * Get the activities.
	 *
	 * @param workspaceSid the workspace sid
	 * @param filters the filters
	 * @return activities matching the filters
	 */
	public ActivityList getActivities(final String workspaceSid, final Map<String, String> filters) {
		ActivityList list = new ActivityList(this, workspaceSid, filters);
		return list;
	}

	/**
	 * Get an activity instance by sid.
	 *
	 * @param workspaceSid The 34 character sid starting with WS
	 * @param activitySid The 34 character sid starting with WA
	 */
	public Activity getActivity(final String workspaceSid, final String activitySid) {
		Activity activity = new Activity(this, workspaceSid, activitySid);
		return activity;
	}

    /**
     * Get an event instance by sid.
     *
     * @param workspaceSid The 34 character sid starting with WS
     * @param eventSid The 34 character sid starting with EV
     */
    public Event getEvent(final String workspaceSid, final String eventSid) {
        Event event = new Event(this, workspaceSid, eventSid);
        return event;
    }

    /**
     * Get the events.
     *
     * @return the events
     */
    public EventList getEvents(final String workspaceSid) {
        return getEvents(workspaceSid, new HashMap<String, String>(0));
    }

    /**
     * Get the events.
     *
     * @param workspaceSid the workspace sid
     * @param filters the filters
     * @return events matching the filters
     */
    public EventList getEvents(final String workspaceSid, final Map<String, String> filters) {
        EventList list = new EventList(this, workspaceSid, filters);
        return list;
    }

	/**
	 * Get a reservation instance by sid.
	 *
	 * @param workspaceSid The 34 character sid starting with WS
	 * @param taskSid The 34 character sid starting with WT
	 * @param reservationSid The 34 character sid starting with WR
	 */
	public Reservation getReservation(final String workspaceSid, final String taskSid, final String reservationSid) {
		Reservation reservation = new Reservation(this, workspaceSid, taskSid, reservationSid);
		return reservation;
	}

	/**
	 * Get the reservations.
	 *
	 * @return the reservations
	 */
	public ReservationList getReservations(final String workspaceSid, final String taskSid) {
		return getReservations(workspaceSid, taskSid, new HashMap<String, String>(0));
	}

	/**
	 * Get the reservations.
	 *
	 * @param workspaceSid the workspace sid
	 * @param filters the filters
	 * @return tasks matching the filters
	 */
	public ReservationList getReservations(final String workspaceSid, final String taskSid,
	                                       final Map<String, String> filters) {
		ReservationList list = new ReservationList(this, workspaceSid, taskSid, filters);
		return list;
	}

	/**
	 * Get a queue instance by sid.
	 *
	 * @param workspaceSid The 34 character sid starting with WS
	 * @param queueSid The 34 character sid starting with WQ
	 */
	public TaskQueue getTaskQueue(final String workspaceSid, final String queueSid) {
		TaskQueue taskQueue = new TaskQueue(this, workspaceSid, queueSid);
		return taskQueue;
	}

	/**
	 * Get the queues.
	 *
	 * @return the queues
	 */
	public TaskQueueList getTaskQueues(final String workspaceSid) {
		return getTaskQueues(workspaceSid, new HashMap<String, String>(0));
	}

	/**
	 * Get the queues.
	 *
	 * @param workspaceSid the workspace sid
	 * @param filters the filters
	 * @return queues matching the filters
	 */
	public TaskQueueList getTaskQueues(final String workspaceSid, final Map<String, String> filters) {
		TaskQueueList list = new TaskQueueList(this, workspaceSid, filters);
		return list;
	}

	/**
	 * Get queues statistics.
	 *
	 * @param workspaceSid The 34 character sid starting with WS
	 * @return queues statistics
	 */
	public TaskQueueListStatistics getQueuesStatistics(final String workspaceSid) {
		return getQueuesStatistics(workspaceSid, null);
	}

	/**
	 * Get queues statistics.
	 *
	 * @param workspaceSid The 34 character sid starting with WS
	 * @param filters the filters
	 * @return queues statistics
	 */
	public TaskQueueListStatistics getQueuesStatistics(final String workspaceSid, final Map<String, String> filters) {
		TaskQueueListStatistics list = new TaskQueueListStatistics(this, workspaceSid, filters);
		return list;
	}

	/**
	 * Get a queue statistics.
	 *
	 * @param workspaceSid The 34 character sid starting with WS
	 * @param queueSid The 34 character sid starting with WQ
	 * @return queue statistics
	 */
	public TaskQueueStatistics getQueueStatistics(final String workspaceSid, final String queueSid) {
		return getQueueStatistics(workspaceSid, queueSid, null);
	}

	/**
	 * Get a queue statistics.
	 *
	 * @param workspaceSid The 34 character sid starting with WS
	 * @param queueSid The 34 character sid starting with WQ
	 * @param filters the filters
	 * @return queue statistics
	 */
	public TaskQueueStatistics getQueueStatistics(final String workspaceSid, final String queueSid,
	                                              final Map<String, String> filters) {
		TaskQueueStatistics taskQueueStatistics = new TaskQueueStatistics(this, workspaceSid, queueSid, filters);
		return taskQueueStatistics;
	}

	/**
	 * Get a task instance by sid.
	 *
	 * @param workspaceSid The 34 character sid starting with WS
	 * @param taskSid The 34 character sid starting with WT
	 */
	public Task getTask(final String workspaceSid, final String taskSid) {
		Task task = new Task(this, workspaceSid, taskSid);
		return task;
	}

	/**
	 * Get the tasks.
	 *
	 * @return the tasks
	 */
	public TaskList getTasks(final String workspaceSid) {
		return getTasks(workspaceSid, new HashMap<String, String>(0));
	}

	/**
	 * Get the tasks.
	 *
	 * @param workspaceSid the workspace sid
	 * @param filters the filters
	 * @return tasks matching the filters
	 */
	public TaskList getTasks(final String workspaceSid, final Map<String, String> filters) {
		TaskList list = new TaskList(this, workspaceSid, filters);
		return list;
	}

	/**
	 * Get a worker instance by sid.
	 *
	 * @param workspaceSid The 34 character sid starting with WS
	 * @param workerSid The 34 character sid starting with WT
	 */
	public Worker getWorker(final String workspaceSid, final String workerSid) {
		Worker worker = new Worker(this, workspaceSid, workerSid);
		return worker;
	}

	/**
	 * Get the workers.
	 *
	 * @return the workers
	 */
	public WorkerList getWorkers(final String workspaceSid) {
		return getWorkers(workspaceSid, new HashMap<String, String>(0));
	}

	/**
	 * Get the workers.
	 *
	 * @param workspaceSid the workspace sid
	 * @param filters the filters
	 * @return tasks matching the filters
	 */
	public WorkerList getWorkers(final String workspaceSid, final Map<String, String> filters) {
		WorkerList list = new WorkerList(this, workspaceSid, filters);
		return list;
	}

	/**
	 * Get workers statistics.
	 *
	 * @param workspaceSid The 34 character sid starting with WS
	 * @return queues statistics
	 */
	public WorkersStatistics getWorkersStatistics(final String workspaceSid) {
		return getWorkersStatistics(workspaceSid, null);
	}

	/**
	 * Get workers statistics.
	 *
	 * @param workspaceSid The 34 character sid starting with WS
	 * @param filters the filters
	 * @return queues statistics
	 */
	public WorkersStatistics getWorkersStatistics(final String workspaceSid, final Map<String, String> filters) {
		WorkersStatistics workersStatistics = new WorkersStatistics(this, workspaceSid, filters);
		return workersStatistics;
	}

	/**
	 * Get worker statistics.
	 *
	 * @param workspaceSid The 34 character sid starting with WS
	 * @param workerSid The 34 character sid starting with WW
	 * @return queues statistics
	 */
	public WorkerStatistics getWorkerStatistics(final String workspaceSid, final String workerSid) {
		return getWorkerStatistics(workspaceSid, workerSid, null);
	}

	/**
	 * Get worker statistics.
	 *
	 * @param workspaceSid The 34 character sid starting with WS
	 * @param workerSid The 34 character sid starting with WW
	 * @param filters the filters
	 * @return queues statistics
	 */
	public WorkerStatistics getWorkerStatistics(final String workspaceSid, final String workerSid,
	                                            final Map<String, String> filters) {
		WorkerStatistics workerStatistics = new WorkerStatistics(this, workspaceSid, workerSid, filters);
		return workerStatistics;
	}

	/**
	 * Get a workflow instance by sid.
	 *
	 * @param workspaceSid The 34 character sid starting with WS
	 * @param workflowSid The 34 character sid starting with WF
	 */
	public Workflow getWorkflow(final String workspaceSid, final String workflowSid) {
		Workflow workflow = new Workflow(this, workspaceSid, workflowSid);
		return workflow;
	}

	/**
	 * Get the workflows.
	 *
	 * @return the workflows
	 */
	public WorkflowList getWorkflows(final String workspaceSid) {
		return getWorkflows(workspaceSid, new HashMap<String, String>(0));
	}

	/**
	 * Get the workflows.
	 *
	 * @param workspaceSid the workspace sid
	 * @param filters the filters
	 * @return tasks matching the filters
	 */
	public WorkflowList getWorkflows(final String workspaceSid, final Map<String, String> filters) {
		WorkflowList list = new WorkflowList(this, workspaceSid, filters);
		return list;
	}

	/**
	 * Get workflow statistics.
	 *
	 * @param workspaceSid The 34 character sid starting with WS
	 * @param workflowSid The 34 character sid starting with WF
	 * @return queues statistics
	 */
	public WorkflowStatistics getWorkflowStatistics(final String workspaceSid, final String workflowSid) {
		return getWorkflowStatistics(workspaceSid, workflowSid, null);
	}

	/**
	 * Get workflow statistics.
	 *
	 * @param workspaceSid The 34 character sid starting with WS
	 * @param workflowSid The 34 character sid starting with WF
	 * @param filters the filters
	 * @return queues statistics
	 */
	public WorkflowStatistics getWorkflowStatistics(final String workspaceSid, final String workflowSid,
	                                                final Map<String, String> filters) {
		WorkflowStatistics workflowStatistics = new WorkflowStatistics(this, workspaceSid, workflowSid, filters);
		return workflowStatistics;
	}

	/**
	 * Get a workspace instance by sid
	 *
	 * @param sid The 34 character sid starting with WS
	 */
	public Workspace getWorkspace(final String sid) {
		Workspace workspace = new Workspace(this, sid);
		return workspace;
	}

	/**
	 * Get the workspaces.
	 *
	 * @return the workspaces
	 */
	public WorkspaceList getWorkspaces() {
		return getWorkspaces(new HashMap<String, String>(0));
	}

	/**
	 * Get the workspaces.
	 *
	 * @param filters the filters
	 * @return workspaces matching the filters
	 */
	public WorkspaceList getWorkspaces(final Map<String, String> filters) {
		WorkspaceList list = new WorkspaceList(this, filters);
		return list;
	}

	/**
	 * Get workspace statistics.
	 *
	 * @param workspaceSid The 34 character sid starting with WS
	 * @return queues statistics
	 */
	public WorkspaceStatistics getWorkspaceStatistics(final String workspaceSid) {
		return getWorkspaceStatistics(workspaceSid, null);
	}

	/**
	 * Get workspace statistics.
	 *
	 * @param workspaceSid The 34 character sid starting with WS
	 * @param filters the filters
	 * @return queues statistics
	 */
	public WorkspaceStatistics getWorkspaceStatistics(final String workspaceSid, final Map<String, String> filters) {
		WorkspaceStatistics workspaceStatistics = new WorkspaceStatistics(this, workspaceSid, filters);
		return workspaceStatistics;
	}

}
