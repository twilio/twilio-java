package com.twilio.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.twilio.converter.Promoter;

import com.google.common.base.MoreObjects;

import java.util.Objects;

/**
 * Subscribe Rule
 *
 * <p>
 *     For more information see:
 *     <a href=https://www.twilio.com/docs/video/api/track-subscriptions#specifying-sr>Specifying Subscribe Rules</a>
 * </p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscribeRule {
    public enum Type {
        INCLUDE("include"),
        EXCLUDE("exclude");

        private final String type;

        Type(final String type) {
            this.type = type;
        }

        @JsonCreator
        public static Type forValue(final String value) {
            return Promoter.enumFromString(value, Type.values());
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("type", this.type)
                    .toString();
        }
    }

    public enum Kind {
        AUDIO("audio"),
        DATA("data"),
        VIDEO("video");

        private final String kind;

        Kind(final String kind) {
            this.kind = kind;
        }

        @JsonCreator
        public static Kind forValue(final String value) {
            return Promoter.enumFromString(value, Kind.values());
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("kind", this.kind)
                    .toString();
        }
    }

    public enum Priority {
        LOW("low"),
        MEDIUM("medium"),
        HIGH("high");

        private final String priority;
        Priority(final String priority) {
            this.priority = priority;
        }

        @JsonCreator
        public static Priority forValue(final String value) {
            return Promoter.enumFromString(value, Priority.values());
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("priority", this.priority)
                    .toString();
        }
    }

    private static final SubscribeRule subscribeAll = builder().withAll(true).withType(Type.INCLUDE).build();
    private static final SubscribeRule subscribeNone = builder().withAll(true).withType(Type.EXCLUDE).build();

    @JsonProperty("type")
    private final Type type;

    @JsonProperty("all")
    private final Boolean all;

    @JsonProperty("publisher")
    private final String publisher;

    @JsonProperty("track")
    private final String track;

    @JsonProperty("kind")
    private final Kind kind;

    @JsonProperty("priority")
    private final Priority priority;

    public SubscribeRule(@JsonProperty("type") final Type type,
            @JsonProperty("all") final Boolean all,
            @JsonProperty("publisher") final String publisher,
            @JsonProperty("track") final String track,
            @JsonProperty("kind") final Kind kind,
            @JsonProperty("priority") final Priority priority) {
        this.type = type;
        this.all = all;
        this.kind = kind;
        this.priority = priority;
        this.publisher = publisher;
        this.track = track;
    }

    public SubscribeRule() {
        this.type = null;
        this.all = null;
        this.kind = null;
        this.priority = null;
        this.publisher = null;
        this.track = null;
    }

    @JsonIgnore
    public boolean isValid() {
        // every rule must have a type
        if (getType() == null) {
            return false;
        }

        // at least one filter must be set
        if (getKind() == null
                && getAll() == null
                && getTrack() == null
                && getPublisher() == null
                && getPriority() == null)
            return false;

        // "all" == false is invalid - if it's present, it must be true
        if (getAll() != null && !getAll()) {
            return false;
        }

        // "all" is exclusive of any other filter
        return getAll() == null
                || (getKind() == null
                && getPriority() == null
                && getPublisher() == null
                && getTrack() == null);
    }

    public static SubscriptionRuleBuilder builder() {
        return new SubscriptionRuleBuilder();
    }

    public static class SubscriptionRuleBuilder {
        private Type type;
        private Boolean all;
        private String publisher;
        private Kind kind;
        private String track;
        private Priority priority;

        private SubscriptionRuleBuilder() {
        }

        public SubscriptionRuleBuilder withType(final Type type) {
            this.type = type;
            return this;
        }
        public SubscriptionRuleBuilder withAll(final Boolean all) {
            this.all = all;
            return this;
        }
        public SubscriptionRuleBuilder withPublisher(final String publisher) {
            this.publisher = publisher;
            return this;
        }
        public SubscriptionRuleBuilder withKind(final Kind kind) {
            this.kind = kind;
            return this;
        }
        public SubscriptionRuleBuilder withTrack(final String track) {
            this.track = track;
            return this;
        }
        public SubscriptionRuleBuilder withPriority(final Priority priority) {
            this.priority = priority;
            return this;
        }

        public SubscribeRule build() {
            return new SubscribeRule(this.type, this.all, this.publisher, this.track, this.kind, this.priority);
        }
    }

    public Type getType() {
        return type;
    }

    public Boolean getAll() {
        return all;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getTrack() {
        return track;
    }

    public Kind getKind() {
        return kind;
    }

    public Priority getPriority() {
        return priority;
    }

    public static SubscribeRule all() {
        return subscribeAll;
    }

    public static SubscribeRule none() {
        return subscribeNone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscribeRule)) return false;
        SubscribeRule that = (SubscribeRule) o;
        return getType() == that.getType() &&
                Objects.equals(getAll(), that.getAll()) &&
                Objects.equals(getPublisher(), that.getPublisher()) &&
                Objects.equals(getTrack(), that.getTrack()) &&
                getKind() == that.getKind() &&
                getPriority() == that.getPriority();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getAll(), getPublisher(), getTrack(), getKind(), getPriority());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("type", this.type)
                .add("all", this.all)
                .add("publisher", this.publisher)
                .add("track", this.track)
                .add("kind", this.kind)
                .add("priority", this.priority)
                .toString();
    }
}
