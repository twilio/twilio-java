package com.twilio.sdk.taskrouter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkflowConfiguration {

	@JsonProperty("task_routing")
	private final TaskRoutingConfiguration taskRouting;
	
	/**
	 * Define a workflow
	 * @param workflowRules list of workflow rules (in order they will be processed)
	 * @param defaultTarget default filter
	 * @throws Exception
	 */
	public WorkflowConfiguration(List<WorkflowRule> workflowRules, WorkflowRuleTarget defaultTarget) throws Exception {
		taskRouting = new TaskRoutingConfiguration(workflowRules, defaultTarget);
	}

	/**
	 * Constructor for creating based on json. 
	 * 
	 * @param taskRoutingConfig
	 * @throws Exception 
	 */
	@JsonCreator
    public WorkflowConfiguration(@JsonProperty("task_routing") TaskRoutingConfiguration taskRouting) {
		this.taskRouting = taskRouting;
    }
	
	@JsonIgnore
	public List<WorkflowRule> getWorkflowRules() {
		return taskRouting.getWorkflowRules();
	}
	
	@JsonIgnore
	public WorkflowRuleTarget getDefaultTarget() {
		return taskRouting.getDefaultTarget();
	}
	
	@Override
	public String toString() {
		return taskRouting.toString();
	}
}

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
class TaskRoutingConfiguration {
	
	@JsonProperty("filters")
	private List<WorkflowRule> workflowRules;
	
	@JsonProperty("default_filter")
	private WorkflowRuleTarget defaultTarget;
	
	/**
	 * Constructor for creating based on json. 
	 * 
	 * @param taskRoutingConfig
	 * @throws Exception 
	 */
	@JsonCreator
	public TaskRoutingConfiguration(@JsonProperty("filters") List<WorkflowRule> workflowRules, 
			@JsonProperty("default_filter") WorkflowRuleTarget defaultTarget) throws Exception {
		if(workflowRules == null || workflowRules.isEmpty()) {
			throw new Exception("Workflow Rules are required when defining a workflow");
		}
		this.workflowRules = workflowRules;
		this.defaultTarget = defaultTarget;
	}
	
	/**
	 * Get the workflow rules for the workflow
	 * @return the list of workflow rules
	 */
	public List<WorkflowRule> getWorkflowRules() {
		return workflowRules;
	}
	
	/**
	 * Set the workflow rules for the workflow
	 * @param workflowRules
	 */
	public void setWorkflowRules(List<WorkflowRule> workflowRules) {
		this.workflowRules = workflowRules;
	}
	
	/**
	 * Get the default filter for the workflow
	 * @return the default filter
	 */
	public WorkflowRuleTarget getDefaultTarget() {
		return defaultTarget;
	}
	
	/**
	 * Set the default filter for the workflow
	 * @param defaultTarget
	 */
	public void setDefaultTarget(WorkflowRuleTarget defaultTarget) {
		this.defaultTarget = defaultTarget;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for(WorkflowRule workflowRule : workflowRules) {
			sb.append("WorkflowRule: "+workflowRule+"\n");
		}
		sb.append("Default Filter: "+defaultTarget);
		return sb.toString();
	}
}
