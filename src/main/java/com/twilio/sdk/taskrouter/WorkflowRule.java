package com.twilio.sdk.taskrouter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class WorkflowRule {

    private String expression;
    private String friendlyName;
    private List<WorkflowRuleTarget> targets;

    /**
     * Define a workflow rule.
     * @param expression expression to match a task to this rule
     * @param targets list of workflow rule targets (list should be provided in order they will be executed)
     * @throws IllegalArgumentException if expression or targets is empty
     */
    public WorkflowRule(final String expression,
                        final List<WorkflowRuleTarget> targets) throws IllegalArgumentException {
        if (StringUtils.isBlank(expression)) {
            throw new IllegalArgumentException("Expression for Workflow Rule is required");
        }
        if (targets == null || targets.isEmpty()) {
            throw new IllegalArgumentException("Targets for Workflow Rule are required");
        }
        this.expression = expression;
        this.targets = targets;
    }

    /**
     * Define a workflow rule with an optional label.
     * @param expression expresison to match a task to this rule
     * @param targets list of workflow rule targets (list should be provided in order they will be executed)
     * @param friendlyName the label for this workflow rule
     * @throws IllegalArgumentException if expression or targets is empty
     */
    @JsonCreator
    public WorkflowRule(@JsonProperty("expression") final String expression,
            @JsonProperty("targets") final List<WorkflowRuleTarget> targets,
            @JsonProperty("friendly_name") final String friendlyName) throws IllegalArgumentException {
        this(expression, targets);
        this.friendlyName = friendlyName;
    }

    /**
     * Get the expression for the workflow rule.
     * @return the expression
     */
    public String getExpression() {
        return expression;
    }

    /**
     * Set the expression for the workflow rule.
     * @param expression expression for the workflow rule
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * Get the friendly name / label for the workflow rule.
     * @return the friendly name
     */
    public String getFriendlyName() {
        return friendlyName;
    }

    /**
     * Set the friendly name for the workflow rule.
     * @param friendlyName friendly name for the workflow rule
     */
    @JsonProperty("friendly_name")
    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    /**
     * Get the filter friendly name / label for the workflow rule.
     * @return the friendly name
     */
    @JsonIgnore
    public String getFilterFriendlyName() {
        return friendlyName;
    }

    /**
     * Set the filter friendly name for the workflow rule.
     * @param filterFriendlyName filter friendly name for the workflow rule.
     */
    @JsonProperty("filter_friendly_name")
    public void setFilterFriendlyName(String filterFriendlyName) {
        this.friendlyName = filterFriendlyName;
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
     * Sets the workflow rule targets.
     * @param targets targets of the workflow rule
     */
    public void setWorkflowRuleTargets(List<WorkflowRuleTarget> targets) {
        this.targets = targets;
    }

    /**
     * Adds the workflow rule targets.
     * @param target target of workflow rule
     */
    public void addWorkflowRuleTarget(WorkflowRuleTarget target) {
        this.targets.add(target);
    }

    /**
     * Return a string representation of this workflow rule target.
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
