package com.twilio.taskrouter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;

import java.io.IOException;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkflowRuleTarget extends TaskRouterResource {

    @JsonProperty("queue")
    private final String queue;

    @JsonProperty("expression")
    private final String expression;

    @JsonProperty("priority")
    private final Integer priority;

    @JsonProperty("timeout")
    private final Integer timeout;

    @JsonCreator
    private WorkflowRuleTarget(
        @JsonProperty("queue") String queue,
        @JsonProperty("expression") String expression,
        @JsonProperty("priority") Integer priority,
        @JsonProperty("timeout") Integer timeout
    ) {
        this.queue = queue;
        this.expression = expression;
        this.priority = priority;
        this.timeout = timeout;
    }

    private WorkflowRuleTarget(Builder b) throws IllegalArgumentException {
        this.queue = b.queue;
        this.expression = b.expression;
        this.priority = b.priority;
        this.timeout = b.timeout;
    }

    /**
     * Get the queue for the workflow rule target.
     * @return queue sid
     */
    public String getQueue() {
        return queue;
    }

    /**
     * Get the expression for the workflow rule target to limit the workers selected.
     * @return the expression
     */
    public String getExpression() {
        return expression;
    }

    /**
     * Get the priority for the workflow rule target.
     * @return the priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * Get the timeout for the workflow rule target.
     * @return the timeout
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * Return a string representation of this workflow rule target.
     * @return string representation of target
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("queue", queue)
            .add("expression", expression)
            .add("priority", priority)
            .add("timeout", timeout)
            .toString();
    }

    /**
     * Converts a JSON workflow configuration to a workflow configuration object.
     *
     * @param json JSON for workflow rule target
     * @return a workflow rule target object
     * @throws IOException if unable to create object
     */
    public static WorkflowRuleTarget fromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, WorkflowRuleTarget.class);
    }

    public static class Builder {

        private String queue;
        private String expression;
        private Integer priority;
        private Integer timeout;

        public Builder(String queue) {
            this.queue = queue;
        }

        public Builder expression(String expression) {
            this.expression = expression;
            return this;
        }

        public Builder priority(Integer priority) {
            this.priority = priority;
            return this;
        }

        public Builder timeout(Integer timeout) {
            this.timeout = timeout;
            return this;
        }

        public WorkflowRuleTarget build() {
            return new WorkflowRuleTarget(this);
        }

    }
}
