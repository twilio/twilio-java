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
public class WorkflowRule extends TaskRouterResource {

    private final String expression;
    private final String friendlyName;
    private final List<WorkflowRuleTarget> targets;

    @JsonCreator
    private WorkflowRule(
        @JsonProperty("expression") String expression,
        @JsonProperty("filter_friendly_name") String filterFriendlyName,
        @JsonProperty("friendly_name") String friendlyName,
        @JsonProperty("targets") List<WorkflowRuleTarget> targets
    ) {
        this.expression = expression;
        this.friendlyName = friendlyName != null ? friendlyName : filterFriendlyName;
        this.targets = targets;
    }

    /**
     * Define a workflow rule.
     * @param b workflow rule builder
     * @throws IllegalArgumentException if expression or targets is empty
     */
    private WorkflowRule(Builder b) {
        this.expression = b.expression;
        this.friendlyName = b.friendlyName;
        this.targets = b.targets;
    }

    /**
     * Get the expression for the workflow rule.
     * @return the expression
     */
    public String getExpression() {
        return expression;
    }

    /**
     * Get the friendly name / label for the workflow rule.
     * @return the friendly name
     */
    public String getFriendlyName() {
        return friendlyName;
    }

    /**
     * Get the list of workflow rule targets for this workflow rule.
     * @return list of workflow rule targets
     */
    @JsonIgnore
    public List<WorkflowRuleTarget> getWorkflowRuleTargets() {
        return targets;
    }

    /**
     * Return a string representation of this workflow rule target.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("expression", expression)
            .add("friendlyName", friendlyName)
            .add("targets", targets)
            .toString();
    }

    /**
     * Converts a JSON workflow configuration to a workflow rule object.
     *
     * @param json JSON for workflow rule
     * @return a workflow rule target object
     * @throws IOException if unable to create object
     */
    public static WorkflowRule fromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, WorkflowRule.class);
    }

    public static class Builder {

        private String expression;
        private String friendlyName;
        private List<WorkflowRuleTarget> targets;

        public Builder(String expression, List<WorkflowRuleTarget> targets) {
            this.expression = expression;
            this.targets = targets;
        }

        public Builder friendlyName(String friendlyName) {
            this.friendlyName = friendlyName;
            return this;
        }

        public WorkflowRule build() {
            return new WorkflowRule(this);
        }

    }
}
