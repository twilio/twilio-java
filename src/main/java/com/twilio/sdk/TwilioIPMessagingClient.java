package com.twilio.sdk;

import com.twilio.sdk.resource.instance.ipmessaging.Credential;
import com.twilio.sdk.resource.instance.ipmessaging.Service;
import com.twilio.sdk.resource.list.ipmessaging.CredentialList;
import com.twilio.sdk.resource.list.ipmessaging.ServiceList;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * Client to interact with https://ip-messaging.twilio.com
 */
public class TwilioIPMessagingClient extends TwilioClient {

	/** The Constant DEFAULT_VERSION. */
	public static final String DEFAULT_VERSION = "v1";

	public TwilioIPMessagingClient(String username, String password) {
		this(username, password, "https://ip-messaging.twilio.com");
	}

	public TwilioIPMessagingClient(String username, String password, String endpoint) {
		super(username, password, endpoint);
	}

	/**
	 * Initialize services endpoint without filters
	 *
	 * @return Services endpoint
	 */
	public ServiceList getServices() {
		return getServices(null);
	}

	/**
	 * Initialize services endpoint with filters
	 *
	 * @param filters The filters to apply
	 * @return Services endpoint
	 */
	public ServiceList getServices(Map<String, String> filters) {
		return new ServiceList(this, filters);
	}

	/**
	 * Initialize a single service
	 *
	 * @param serviceSid The service sid
	 * @return The service
	 */
	public Service getService(String serviceSid) {
		return new Service(this, serviceSid);
	}

	/**
	 * Create a service
	 *
	 * @param params The create parameters
	 * @return The created service
	 * @throws TwilioIPMessagingClient thrown on error
	 */
	public Service createService(Map<String, String> params) throws TwilioRestException {
		return getServices().create(params);
	}

	/**
	 * Create a service
	 *
	 * @param params The create parameters
	 * @return The created service
	 * @throws TwilioIPMessagingClient thrown on error
	 */
	public Service createService(List<NameValuePair> params) throws TwilioRestException {
		return getServices().create(params);
	}

	/**
	 * Initialize credentials endpoint without filters
	 *
	 * @return Credentials endpoint
	 */
	public CredentialList getCredentials() {
		return getCredentials(null);
	}

	/**
	 * Initialize credentials endpoint with filters
	 *
	 * @param filters The filters to apply
	 * @return Credentials endpoint
	 */
	public CredentialList getCredentials(Map<String, String> filters) {
		return new CredentialList(this, filters);
	}

	/**
	 * Create a new credential
	 *
	 * @param params The params to create with
	 * @return The create credential
	 * @throws TwilioRestException thrown on http error
	 */
	public Credential createCredential(Map<String, String> params) throws TwilioRestException {
		return getCredentials().create(params);
	}

	/**
	 * Create a new credential
	 *
	 * @param params The params to create with
	 * @return The create credential
	 * @throws TwilioRestException thrown on http error
	 */
	public Credential createCredential(List<NameValuePair> params) throws TwilioRestException {
		return getCredentials().create(params);
	}

	/**
	 * Initialize a single credential
	 *
	 * @param credentialSid The credential sid
	 * @return The Credential
	 */
	public Credential getCredential(String credentialSid) {
		return new Credential(this, credentialSid);
	}
}
