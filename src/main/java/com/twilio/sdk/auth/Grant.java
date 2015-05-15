package com.twilio.sdk.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

/**
 * A grant is given to a resource for a specified set of actions.
 */
public class Grant {

	private String resource;
	private Set<Action> actions;

	public Grant(final String resource) {
		this(resource, Action.ALL);
	}

	public Grant(final String resource, final Action action) {
		this(resource, new HashSet<Action>(1) {{ add(action); }});
	}

	/**
	 * Instantiate a Grant.
	 *
	 * @param resource the resource
	 * @param actions the actions
	 */
	public Grant(final String resource, final Set<Action> actions) {
		this.resource = resource;
		this.actions = actions;
	}

	/**
	 * Get the resource
	 *
	 * @return the resource
	 */
	@JsonProperty("res")
	public String getResource() {
		return resource;
	}

	/**
	 * Get the actions.
	 *
	 * @return the actions
	 */
	@JsonProperty("act")
	public Set<Action> getActions() {
		return actions;
	}
}
