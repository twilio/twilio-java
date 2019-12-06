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
    
    @JsonProperty("order_by")
    private final String orderBy;
    
    @JsonProperty("skip_if")
    private final String skipIf;

    @JsonCreator
    private WorkflowRuleTarget(
        @JsonProperty("queue") String queue,
        @JsonProperty("expression") String expression,
        @JsonProperty("priority") Integer priority,
        @JsonProperty("timeout") Integer timeout,
        @JsonProperty("order_by") String orderBy,
        @JsonProperty("skip_if") String skipIf
    ) {
        this.queue = queue;
        this.expression = expression;
        this.priority = priority;
        this.timeout = timeout;
        this.orderBy = orderBy;
        this.skipIf = skipIf;
    }

    private WorkflowRuleTarget(Builder b) throws IllegalArgumentException {
        this.queue = b.queue;
        this.expression = b.expression;
        this.priority = b.priority;
        this.timeout = b.timeout;
        this.orderBy = b.orderBy;
        this.skipIf = b.skipIf;
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
     * Get the orderBy for the workflow rule target.
     * @return the orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * Get the skipIf for the workflow rule target.
     * @return the skipIf
     */
    public String getSkipIf() {
        return skipIf;
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
            .add("orderBy", orderBy)
            .add("skipIf", skipIf)
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
        private String orderBy;
        private String skipIf;

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
        
        public Builder orderBy(String orderBy) {
            this.orderBy = orderBy;
            return this;
        }
        
        public Builder skipIf(String skipIf) {
            this.skipIf = skipIf;
            return this;
        }

        public WorkflowRuleTarget build() {
            return new WorkflowRuleTarget(this);
        }

    }
}
