package com.twilio.taskrouter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * Created by jniu on 3/29/16.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TaskRouting {

    @JsonProperty("filters")
    private final List<WorkflowRule> workflowRules;

    @JsonProperty("default_filter")
    private final WorkflowRuleTarget defaultTarget;

    /**
     * Constructor for creating based on json..
     *
     * @param workflowRules Workflow Rule configuration
     * @param defaultTarget Default Rule target
     */
    @JsonCreator
    public TaskRouting(
        @JsonProperty("filters") List<WorkflowRule> workflowRules,
        @JsonProperty("default_filter") WorkflowRuleTarget defaultTarget
    ) {
        this.workflowRules = workflowRules;
        this.defaultTarget = defaultTarget;
    }

    /**
     * Get the workflow rules for the workflow.
     * @return the list of workflow rules
     */
    public List<WorkflowRule> getWorkflowRules() {
        return workflowRules;
    }

    /**
     * Get the default filter for the workflow.
     * @return the default filter
     */
    public WorkflowRuleTarget getDefaultTarget() {
        return defaultTarget;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
