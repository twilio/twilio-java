package com.twilio.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.twilio.converter.Promoter;

public interface Rule {

    Type getType();

    Boolean getAll();

    String getPublisher();

    String getTrack();

    Kind getKind();

    enum Type {
        INCLUDE("include"),
        EXCLUDE("exclude");

        private final String value;

        Type(final String value) {
            this.value = value;
        }

        @JsonCreator
        public static Type forValue(final String value) {
            return Promoter.enumFromString(value, Rule.Type.values());
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    enum Kind {
        AUDIO("audio"),
        DATA("data"),
        VIDEO("video");

        private final String value;

        Kind(final String value) {
            this.value = value;
        }

        @JsonCreator
        public static Kind forValue(final String value) {
            return Promoter.enumFromString(value, Rule.Kind.values());
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    enum Priority {
        LOW("low"),
        STANDARD("standard"),
        HIGH("high");

        private final String value;

        Priority(final String value) {
            this.value = value;
        }

        @JsonCreator
        public static Priority forValue(final String value) {
            return Promoter.enumFromString(value, Priority.values());
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
