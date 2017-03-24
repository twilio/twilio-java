package com.twilio.taskrouter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;

import java.io.IOException;
import java.util.List;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Workflow extends TaskRouterResource {

    @JsonProperty("task_routing")
    private final TaskRouting taskRouting;

    /**
     * Define a workflow.
     *
     * @param workflowRules list of workflow rules (in order they will be processed)
     * @param defaultTarget default filter
     */
    public Workflow(List<WorkflowRule> workflowRules, WorkflowRuleTarget defaultTarget) {
        taskRouting = new TaskRouting(workflowRules, defaultTarget);
    }

    /**
     * Constructor for creating based on json.
     *
     * @param taskRouting Task Routing Configuration
     */
    @JsonCreator
    private Workflow(@JsonProperty("task_routing") TaskRouting taskRouting) {
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
        return MoreObjects.toStringHelper(this)
            .add("taskRouting", taskRouting)
            .toString();
    }

    /**
     * Converts a JSON workflow configuration to a workflow object.
     *
     * @param json JSON for workflow
     * @return a workflow rule target object
     * @throws IOException if unable to create object
     */
    public static Workflow fromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Workflow.class);
    }
}