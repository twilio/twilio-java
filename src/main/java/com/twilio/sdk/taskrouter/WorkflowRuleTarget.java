package com.twilio.sdk.taskrouter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkflowRuleTarget {

	private String queue;
	private String expression;
	private Integer priority;
	private Integer timeout;
	
	/**
	 * Create a workflow rule target with just a task queue sid
	 * @param queue sid of the queue
	 * @throws Exception
	 */
	public WorkflowRuleTarget(final String queue) throws Exception {
		if(queue == null || queue.isEmpty()) {
			throw new Exception("QueueSid is required when defining a Workflow Rule Target");
		}
		this.queue = queue;
	}
	
	/**
	 * Create a workflow rule target with a task queue and other optional fields
	 * @param queue sid of the queue
	 * @param expression expression to limit the workers that can look at a given task
	 * @param priority priority to assign the task
	 * @param timeout timeout before moving on to the next workflow rule target
	 * @throws Exception
	 */
	@JsonCreator
	public WorkflowRuleTarget(@JsonProperty("queue") final String queue, 
			@JsonProperty("expression") final String expression, 
			@JsonProperty("priority") final Integer priority, 
			@JsonProperty("timeout") final Integer timeout) throws Exception {
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
		StringBuffer sb = new StringBuffer();
		sb.append("\n\t\tQueue: "+queue);
		if(expression != null) {
			sb.append("\n\t\tExpression: "+expression);
		}
		if(priority != null) {
			sb.append("\n\t\tPriority: "+priority);
		}
		if(timeout != null) {
			sb.append("\n\t\tTimeout: "+priority);
		}
		return sb.toString();
	}
}
