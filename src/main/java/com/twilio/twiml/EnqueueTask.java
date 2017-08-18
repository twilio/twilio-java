package com.twilio.twiml;


import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/enqueue.
 *
 * <p>Use this one if you are using TaskRouter tasks.</p>
 */
@XmlRootElement(name = "Enqueue")
public class EnqueueTask extends TwiML {

    @XmlAttribute
    private final String action;

    @XmlAttribute
    private final Method method;

    @XmlAttribute
    private final String waitUrl;

    @XmlAttribute
    private final Method waitUrlMethod;

    @XmlAttribute
    private final String workflowSid;

    @XmlElement(name = "Task")
    private final Task task;

    // For XML Serialization
    private EnqueueTask() {
        this(new Builder(null));
    }

    private EnqueueTask(Builder b) {
        this.action = b.action;
        this.method = b.method;
        this.waitUrl = b.waitUrl;
        this.waitUrlMethod = b.waitUrlMethod;
        this.workflowSid = b.workflowSid;
        this.task = b.task;
    }

    public String getAction() {
        return action;
    }

    public Method getMethod() {
        return method;
    }

    public String getWaitUrl() {
        return waitUrl;
    }

    public Method getWaitUrlMethod() {
        return waitUrlMethod;
    }

    public String getWorkflowSid() {
        return workflowSid;
    }

    public Task getTask() {
        return task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnqueueTask that = (EnqueueTask) o;
        return Objects.equal(action, that.action) &&
            method == that.method &&
            Objects.equal(waitUrl, that.waitUrl) &&
            waitUrlMethod == that.waitUrlMethod &&
            Objects.equal(workflowSid, that.workflowSid) &&
            Objects.equal(task, that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(action, method, waitUrl, waitUrlMethod, workflowSid, task);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("action", action)
            .add("method", method)
            .add("waitUrl", waitUrl)
            .add("waitUrlMethod", waitUrlMethod)
            .add("workflowSid", workflowSid)
            .add("task", task)
            .toString();
    }

    public static class Builder {
        private String action;
        private Method method;
        private String waitUrl;
        private Method waitUrlMethod;
        private String workflowSid;
        private Task task;

        public Builder(Task task) {
            this.task = task;
        }

        public Builder action(String action) {
            this.action = action;
            return this;
        }

        public Builder method(Method method) {
            this.method = method;
            return this;
        }

        public Builder waitUrl(String waitUrl) {
            this.waitUrl = waitUrl;
            return this;
        }

        public Builder waitUrlMethod(Method waitUrlMethod) {
            this.waitUrlMethod = waitUrlMethod;
            return this;
        }

        public Builder workflowSid(String workflowSid) {
            this.workflowSid = workflowSid;
            return this;
        }

        public EnqueueTask build() {
            return new EnqueueTask(this);
        }
    }
}
