package com.twilio.sdk.resource.factory.taskrouter;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.taskrouter.Workflow;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * A factory for creating Workflows.
 */
public interface WorkflowFactory {

	/**
	 * Creates a Workflow.
	 *
	 * @param params the params list
	 * @return a Workflow
	 * @throws com.twilio.sdk.TwilioRestException
	 */
	public Workflow create(Map<String, String> params) throws TwilioRestException;

	/**
	 * Creates a Workflow.
	 *
	 * @param params the params list
	 * @return a Workflow
	 * @throws TwilioRestException
	 */
	public Workflow create(List<NameValuePair> params) throws TwilioRestException;
}
