package com.twilio.taskrouter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.IOException;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class WorkflowRuleTarget extends TaskRouterResource {

    @JsonProperty("queue")
    private final String queue;

    @JsonProperty("expression")
    private final String expression;

    @JsonProperty("priority")
    private final Integer priority;

    @JsonProperty("timeout")
    private final Integer timeout;

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
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
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
        return mapper.readValue(json, Builder.class).build();
    }

    public static class Builder {

        private String queue;
        private String expression;
        private Integer priority;
        private Integer timeout;

        @JsonCreator
        private Builder(
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
