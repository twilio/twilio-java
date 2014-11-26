package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.Objects;

public class CallFeedbackSummaryIssue {

    private int count;

    private String description;

    private double percentageOfTotalCalls;

    @JsonCreator
    private CallFeedbackSummaryIssue(@JsonProperty("count") final int count,
                                     @JsonProperty("description") final String description, @JsonProperty(
            "percentage_of_total_calls") final String percentageOfTotalCalls) {
        this.count = count;
        this.description = description;
        this.percentageOfTotalCalls = Double.valueOf(percentageOfTotalCalls.replace("%", ""));
    }

    public int getCount() {
        return count;
    }

    public String getDescription() {
        return description;
    }

    public double getPercentageOfTotalCalls() {
        return percentageOfTotalCalls;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CallFeedbackSummaryIssue self = (CallFeedbackSummaryIssue) o;

        return (Objects.equals(count, self.count) &&
                Objects.equals(description, self.description) &&
                Objects.equals(percentageOfTotalCalls, self.percentageOfTotalCalls));
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, description, percentageOfTotalCalls);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("count", count)
                          .add("description", description)
                          .add("percentageOfTotalCalls", percentageOfTotalCalls)
                          .toString();
    }
}
