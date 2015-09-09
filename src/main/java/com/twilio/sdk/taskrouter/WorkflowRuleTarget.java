package com.twilio.sdk.taskrouter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WorkflowRuleTarget {

	private String queue;
	private String expression;
	private Integer priority;
	private Integer timeout;

	/**
	 * Create a workflow rule target with just a task queue sid
	 * @param queue sid of the queue
	 * @throws IllegalArgumentException
	 */
	public WorkflowRuleTarget(final String queue) throws IllegalArgumentException {
		if(StringUtils.isBlank(queue)) {
			throw new IllegalArgumentException("QueueSid is required when defining a Workflow Rule Target");
		}
		this.queue = queue;
	}

	/**
	 * Create a workflow rule target with a task queue and other optional fields
	 * @param queue sid of the queue
	 * @param expression expression to limit the workers that can look at a given task
	 * @param priority priority to assign the task
	 * @param timeout timeout before moving on to the next workflow rule target
	 * @throws IllegalArgumentException
	 */
	@JsonCreator
	public WorkflowRuleTarget(@JsonProperty("queue") final String queue, 
			@JsonProperty("expression") final String expression, 
			@JsonProperty("priority") final Integer priority, 
			@JsonProperty("timeout") final Integer timeout) throws IllegalArgumentException {
		this(queue);
		this.expression = expression;
		this.priority = priority;
		this.timeout = timeout;
	}

	/**
	 * Get the queue for the workflow rule target
	 * @return queue sid
	 */
	public String getQueue() {
		return queue;
	}

	/**
	 * Set the queue for the workflow rule target
	 */
	public void setQueue(String queue) {
		this.queue = queue;
	}

	/**
	 * Get the expression for the workflow rule target to limit the workers selected
	 * @return the expression
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * Set the expression for the workflow rule target
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}

	/**
	 * Get the priority for the workflow rule target
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * Set the priority for the workflow rule target
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * Get the timeout for the workflow rule target
	 * @return the timeout
	 */
	public Integer getTimeout() {
		return timeout;
	}

	/**
	 * Set the timeout for the workflow rule target
	 */
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	/**
	 * Return a string representation of this workflow rule target
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
