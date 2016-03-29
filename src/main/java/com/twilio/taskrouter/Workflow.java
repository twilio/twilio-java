package com.twilio.taskrouter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Workflow {

    @JsonProperty("task_routing")
    private final TaskRouting taskRouting;

    /**
     * Define a workflow.
     *
     * @param workflowRules list of workflow rules (in order they will be processed)
     * @param defaultTarget default filter
     * @throws Exception if unable to create Workflow
     */
    public Workflow(List<WorkflowRule> workflowRules, WorkflowRuleTarget defaultTarget) throws Exception {
        taskRouting = new TaskRouting(workflowRules, defaultTarget);
    }

    /**
     * Constructor for creating based on json.
     *
     * @param taskRouting Task Routing Configuration
     */
    @JsonCreator
    public Workflow(@JsonProperty("task_routing") TaskRouting taskRouting) {
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
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}