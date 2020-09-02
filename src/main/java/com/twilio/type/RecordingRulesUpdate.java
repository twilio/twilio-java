package com.twilio.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordingRulesUpdate {

        @JsonProperty("rules")
        private final List<RecordingRule> rules;

        public RecordingRulesUpdate(@JsonProperty("rules") final List<RecordingRule> rules) {
            this.rules = rules;
        }

        public List<RecordingRule> getRules() {
            return rules;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final com.twilio.type.RecordingRulesUpdate that = (com.twilio.type.RecordingRulesUpdate) o;
            return Objects.equals(getRules(), that.getRules());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getRules());
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("rules", this.rules)
                    .toString();
        }
}
