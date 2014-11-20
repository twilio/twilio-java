package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.InstanceResource;

import java.util.*;

public class Token extends InstanceResource {

	/**
	 * Instantiates a new token.
	 *
	 * @param client the client
	 */
	public Token(TwilioRestClient client) {
		super(client);
	}

    @Override
    protected String getResourceLocation() {
        return null;
    }

    /**
	 * Instantiates a new token.
	 *
	 * @param client the client
	 * @param username the username
	 */
	public Token(TwilioRestClient client, String username) {
		super(client);
		if (username == null) {
			throw new IllegalStateException("The username for a Token can not be null");
		}
		this.setProperty("username", username);
	}

	/**
	 * Instantiates a new token.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Token(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/*
	 * Property getters
	 */

	/**
	 * Gets the sid.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return this.getProperty("username");
	}

	/**
	 * Gets the date created.
	 *
	 * @return the date created
	 */
	public Date getDateCreated() {
		return parseDate(this.getProperty("date_created"));
	}

	/**
	 * Gets the date updated.
	 *
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		return parseDate(this.getProperty("date_updated"));
	}

	/**
	 * Gets the account sid.
	 *
	 * @return the account sid
	 */
	public String getAccountSid() {
		return this.getProperty("account_sid");
	}

	/**
	 * Gets the username
	 *
	 * @return the username
	 */
	public String getUsername() {
		return this.getProperty("username");
	}

	/**
	 * Gets the password
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.getProperty("password");
	}

	/**
	 * Gets the ttl
	 *
	 * @return the ttl
	 */
	public int getTtl() {
		return Integer.parseInt(this.getProperty("ttl"));
	}

	/**
	 *
	 * An IceServer is a plain old java object that
	 * stores the url of the server, and an optional
	 * username and credential.
	 */
	class IceServer {
		public final String url;
		public final String username;
		public final String credential;

		public IceServer(Map<String, String> params) {
			String serverUrl = null;
			String serverUsername = null;
			String serverCredential = null;
			if (params.containsKey("url")) {
				serverUrl = params.get("url");
			}
			if (params.containsKey("username")) {
				serverUsername = params.get("username");
			}
			if (params.containsKey("credential")) {
				serverCredential = params.get("credential");
			}
			this.url = serverUrl;
			this.username = serverUsername;
			this.credential = serverCredential;
		}


		/**
		 * Check if username is set for this server.
		 *
		 * @return true if username exists
		 */
		public boolean hasUsername() {
			return this.url != null;
		}

		/**
		 * Check if a credential is set for this server.
		 *
		 * @return true if credential exists
		 */
		public boolean hasCredential() {
			return this.url != null;
		}

		/**
		 * Get the url.
		 *
		 * @return the url
		 */
		public String getUrl() {
			return this.url;
		}

		/**
		 * Get the username.
		 *
		 * @return the username
		 */
		public String getUsername() {
			return this.username;
		}

		/**
		 * Get the credential.
		 *
		 * @return the credential
		 */
		public String getCredential() {
			return this.credential;
		}
	}

	/**
	 * Returns the ice servers associated with this token.
	 *
	 * @return the list of ice servers.
	 */
	public List<IceServer> getIceServers() {

		List<IceServer> iceServers = new ArrayList<IceServer>();
		for (Map<String, String> server : (List<HashMap<String, String>>) this.getObject("ice_servers")) {
			IceServer token = new IceServer(server);
			iceServers.add(token);
		}
		return iceServers;
	}

}
