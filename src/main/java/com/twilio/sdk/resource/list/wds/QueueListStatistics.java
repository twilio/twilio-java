package com.twilio.sdk.resource.list.wds;

import com.twilio.sdk.TwilioWdsClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.wds.QueueStatistics;

import java.util.Map;

/**
 * QueueListStatistics to work with {@link com.twilio.sdk.resource.instance.wds.QueueStatistics}.
 */
public class QueueListStatistics extends ListResource<QueueStatistics, TwilioWdsClient> {

	private String workspaceSid;

	/**
	 * Instantiates a queue list statistics.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 */
	public QueueListStatistics(final TwilioWdsClient client, final String workspaceSid) {
		super(client);
		this.workspaceSid = workspaceSid;
	}

	/**
	 * Instantiates a queue list statistics.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param filters the filters
	 */
	public QueueListStatistics(final TwilioWdsClient client, final String workspaceSid,
	                           final Map<String, String> filters) {
		super(client, filters);
		this.workspaceSid = workspaceSid;
	}

	@Override
	protected QueueStatistics makeNew(final TwilioWdsClient client, final Map<String, Object> params) {
		return new QueueStatistics(client, params);
	}

	@Override
	protected String getListKey() {
		return "task_queues_statistics";
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioWdsClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Workspaces/" +
		       workspaceSid + "Statistics/TaskQueues";
	}
}
