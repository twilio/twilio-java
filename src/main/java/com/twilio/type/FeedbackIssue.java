package com.twilio.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.Objects;

/**
 * Representation of a Feedback Issue.
 *
 * <p>
 *     For more information see:
 *     <a href=https://www.twilio.com/docs/api/rest/call-feedback>Feedback docs</a>
 * </p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedbackIssue {
    private final int count;
    private final String description;
    private final String percentageOfTotalCalls;

    /**
     * Initialize a FeedbackIssue.
     *
     * @param count number of times reported
     * @param description description of issue
     * @param percentageOfTotalCalls percentage of affected calls
     */
    @JsonCreator
    public FeedbackIssue(@JsonProperty("count") final int count,
                         @JsonProperty("description") final String description,
                         @JsonProperty("percentage_of_total_calls") final String percentageOfTotalCalls) {
        this.count = count;
        this.description = description;
        this.percentageOfTotalCalls = percentageOfTotalCalls;
    }

    public int getCount() {
        return count;
    }

    public String getDescription() {
        return description;
    }

    public String getPercentageOfTotalCalls() {
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

        FeedbackIssue other = (FeedbackIssue) o;
        return Objects.equals(this.count, other.count) &&
               Objects.equals(this.description, other.description) &&
               Objects.equals(this.percentageOfTotalCalls, other.getPercentageOfTotalCalls());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.count, this.description, this.percentageOfTotalCalls);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("count", count)
                .add("description", description)
                .add("percentage_of_total_calls", percentageOfTotalCalls)
                .toString();
    }
}
