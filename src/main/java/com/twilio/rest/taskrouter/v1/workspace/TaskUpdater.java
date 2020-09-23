/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.taskrouter.v1.workspace;

import com.twilio.base.Updater;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class TaskUpdater extends Updater<Task> {
    private final String pathWorkspaceSid;
    private final String pathSid;
    private String attributes;
    private Task.Status assignmentStatus;
    private String reason;
    private Integer priority;
    private String taskChannel;

    /**
     * Construct a new TaskUpdater.
     *
     * @param pathWorkspaceSid The SID of the Workspace with the Task to update
     * @param pathSid The SID of the resource to update
     */
    public TaskUpdater(final String pathWorkspaceSid,
                       final String pathSid) {
        this.pathWorkspaceSid = pathWorkspaceSid;
        this.pathSid = pathSid;
    }

    /**
     * The JSON string that describes the custom attributes of the task..
     *
     * @param attributes The JSON string that describes the custom attributes of
     *                   the task
     * @return this
     */
    public TaskUpdater setAttributes(final String attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * The new status of the task. Can be: `canceled`, to cancel a Task that is
     * currently `pending` or `reserved`; `wrapping`, to move the Task to wrapup
     * state; or `completed`, to move a Task to the completed state..
     *
     * @param assignmentStatus The new status of the task
     * @return this
     */
    public TaskUpdater setAssignmentStatus(final Task.Status assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
        return this;
    }

    /**
     * The reason that the Task was canceled or completed. This parameter is
     * required only if the Task is canceled or completed. Setting this value queues
     * the task for deletion and logs the reason..
     *
     * @param reason The reason that the Task was canceled or complete
     * @return this
     */
    public TaskUpdater setReason(final String reason) {
        this.reason = reason;
        return this;
    }

    /**
     * The Task's new priority value. When supplied, the Task takes on the specified
     * priority unless it matches a Workflow Target with a Priority set. Value can
     * be 0 to 2^31^ (2,147,483,647)..
     *
     * @param priority The Task's new priority value
     * @return this
     */
    public TaskUpdater setPriority(final Integer priority) {
        this.priority = priority;
        return this;
    }

    /**
     * When MultiTasking is enabled, specify the TaskChannel with the task to
     * update. Can be the TaskChannel's SID or its `unique_name`, such as `voice`,
     * `sms`, or `default`..
     *
     * @param taskChannel When MultiTasking is enabled, specify the TaskChannel
     *                    with the task to update
     * @return this
     */
    public TaskUpdater setTaskChannel(final String taskChannel) {
        this.taskChannel = taskChannel;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Task
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Task update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.TASKROUTER.toString(),
            "/v1/Workspaces/" + this.pathWorkspaceSid + "/Tasks/" + this.pathSid + ""
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Task update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Task.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (attributes != null) {
            request.addPostParam("Attributes", attributes);
        }

        if (assignmentStatus != null) {
            request.addPostParam("AssignmentStatus", assignmentStatus.toString());
        }

        if (reason != null) {
            request.addPostParam("Reason", reason);
        }

        if (priority != null) {
            request.addPostParam("Priority", priority.toString());
        }

        if (taskChannel != null) {
            request.addPostParam("TaskChannel", taskChannel);
        }
    }
}