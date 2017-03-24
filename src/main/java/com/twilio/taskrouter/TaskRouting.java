package com.twilio.taskrouter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;

import java.io.IOException;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskRouting extends TaskRouterResource {

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
        return MoreObjects.toStringHelper(this)
            .add("workflowRules", workflowRules)
            .add("defaultTarget", defaultTarget)
            .toString();
    }

    /**
     * Converts a JSON workflow configuration to a workflow rule object.
     *
     * @param json JSON for workflow rule
     * @return a workflow rule target object
     * @throws IOException if unable to create object
     */
    public static TaskRouting fromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TaskRouting.class);
    }
}
