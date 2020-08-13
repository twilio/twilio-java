package com.twilio.type;

import com.google.common.base.MoreObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * Subscribe Rule Update - Used to update the list of Subscribe Rules
 *
 * <p>
 *     For more information see:
 *     <a href=https://www.twilio.com/docs/video/api/track-subscriptions#specifying-sr>Specifying Subscribe Rules</a>
 * </p>
 */
public class SubscribeRulesUpdate {
    @JsonProperty("rules")
    private final List<SubscribeRule> rules;

    public SubscribeRulesUpdate(@JsonProperty("rules") final List<SubscribeRule> rules) {
        this.rules = rules;
    }

    public List<SubscribeRule> getRules() {
        return rules;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SubscribeRulesUpdate that = (SubscribeRulesUpdate) o;
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
