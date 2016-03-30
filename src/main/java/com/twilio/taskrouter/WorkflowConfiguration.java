package com.twilio.taskrouter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.MoreObjects;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class WorkflowConfiguration {

    @JsonProperty("task_routing")
    private final TaskRoutingConfiguration taskRouting;

    /**
     * Define a workflow.
     *
     * @param workflowRules list of workflow rules (in order they will be processed)
     * @param defaultTarget default filter
     * @throws Exception if unable to create WorkflowConfiguration
     */
    public WorkflowConfiguration(List<WorkflowRule> workflowRules, WorkflowRuleTarget defaultTarget) throws Exception {
        taskRouting = new TaskRoutingConfiguration(workflowRules, defaultTarget);
    }

    /**
     * Constructor for creating based on json.
     *
     * @param taskRouting Task Routing Configuration
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
        return MoreObjects.toStringHelper(this)
            .add("taskRouting", taskRouting)
            .toString();
    }

    /**
     * Converts a workflow configuration to JSON.
     * @return JSON for workflow configuration
     * @throws IOException if unable to transform to JSON
     */
    @SuppressWarnings("checkstyle:abbreviationaswordinname")
    public String toJSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        mapper.writeValue(out, this);
        return out.toString();
    }

    /**
     * Converts a JSON workflow configuration to a workflow configuration object.
     * @param configurationJson JSON for workflow configuration
     * @return a workflow configuration object
     * @throws IOException if unable to create object
     */
    public static WorkflowConfiguration parse(final String configurationJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(configurationJson, WorkflowConfiguration.class);
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    static class TaskRoutingConfiguration {

        @JsonProperty("filters")
        private List<WorkflowRule> workflowRules;

        @JsonProperty("default_filter")
        private WorkflowRuleTarget defaultTarget;

        /**
         * Constructor for creating based on json..
         *
         * @param workflowRules Workflow Rule configuration
         * @param defaultTarget Default Rule target
         * @throws Exception if unable to create configuration
         */
        @JsonCreator
        public TaskRoutingConfiguration(@JsonProperty("filters") List<WorkflowRule> workflowRules,
                @JsonProperty("default_filter") WorkflowRuleTarget defaultTarget) throws Exception {
            if (workflowRules == null || workflowRules.isEmpty()) {
                throw new Exception("Workflow Rules are required when defining a workflow");
            }
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
         * Set the workflow rules for the workflow.
         * @param workflowRules Workflow Rule configuration
         */
        public void setWorkflowRules(List<WorkflowRule> workflowRules) {
            this.workflowRules = workflowRules;
        }

        /**
         * Get the default filter for the workflow.
         * @return the default filter
         */
        public WorkflowRuleTarget getDefaultTarget() {
            return defaultTarget;
        }

        /**
         * Set the default filter for the workflow.
         * @param defaultTarget Default Rule target
         */
        public void setDefaultTarget(WorkflowRuleTarget defaultTarget) {
            this.defaultTarget = defaultTarget;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                .add("workflowRules", workflowRules)
                .add("defaultTarget", defaultTarget)
                .toString();
        }
    }
}