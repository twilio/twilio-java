package com.twilio.sdk.resource.factory.taskrouter;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.taskrouter.Workspace;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * A factory for creating Workspaces.
 */
public interface WorkspaceFactory {

	/**
	 * Creates a workspace.
	 *
	 * @param params the params list
	 * @return a workspace
	 * @throws TwilioRestException
	 */
	public Workspace create(Map<String, String> params) throws TwilioRestException;

	/**
	 * Creates a workspace.
	 *
	 * @param params the params list
	 * @return a workspace
	 * @throws TwilioRestException
	 */
	public Workspace create(List<NameValuePair> params) throws TwilioRestException;

}
