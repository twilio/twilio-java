package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.creators.CallFeedbackSummaryCreator;
import com.twilio.sdk.deleters.CallFeedbackSummaryDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.CallFeedbackSummaryFetcher;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CallFeedbackSummary extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final Boolean includeSubaccounts;
    private final CallFeedbackSummary.Status status;
    private final Double qualityScoreStandardDeviation;
    private final LocalDate endDate;
    private final Integer callFeedbackCount;
    private final DateTime dateUpdated;
    private final List<CallFeedbackSummaryIssue> issues;
    private final String accountSid;
    private final Double qualityScoreMedian;
    private final Double qualityScoreAverage;
    private final String sid;
    private final DateTime dateCreated;
    private final LocalDate startDate;
    private final Integer callCount;

    @JsonCreator
    private CallFeedbackSummary(@JsonProperty("include_subaccounts") final Boolean includeSubaccounts,
                                @JsonProperty("status") final CallFeedbackSummary.Status status, @JsonProperty(
            "quality_score_standard_deviation") final Double qualityScoreStandardDeviation,
                                @JsonProperty("end_date") final String endDate,
                                @JsonProperty("call_feedback_count") final Integer callFeedbackCount,
                                @JsonProperty("date_updated") final String dateUpdated,
                                @JsonProperty("issues") final List<CallFeedbackSummaryIssue> issues,
                                @JsonProperty("account_sid") final String accountSid,
                                @JsonProperty("quality_score_median") final Double qualityScoreMedian,
                                @JsonProperty("quality_score_average") final Double qualityScoreAverage,
                                @JsonProperty("sid") final String sid,
                                @JsonProperty("date_created") final String dateCreated,
                                @JsonProperty("start_date") final String startDate,
                                @JsonProperty("call_count") final Integer callCount) {
        this.includeSubaccounts = includeSubaccounts;
        this.status = status;
        this.qualityScoreStandardDeviation = qualityScoreStandardDeviation;
        this.endDate = safeDateConvert(endDate);
        this.callFeedbackCount = callFeedbackCount;
        this.dateUpdated = safeDateTimeConvert(dateUpdated);
        this.issues = issues;
        this.accountSid = accountSid;
        this.qualityScoreMedian = qualityScoreMedian;
        this.qualityScoreAverage = qualityScoreAverage;
        this.sid = sid;
        this.dateCreated = safeDateTimeConvert(dateCreated);
        this.startDate = safeDateConvert(startDate);
        this.callCount = callCount;

    }

    public static CallFeedbackSummaryCreator create(final LocalDate startDate, final LocalDate endDate) {
        return new CallFeedbackSummaryCreator(startDate, endDate);
    }

    public static CallFeedbackSummaryDeleter delete(final String sid) {
        return new CallFeedbackSummaryDeleter(sid);
    }

    public static CallFeedbackSummaryDeleter delete(final CallFeedbackSummary target) {
        return new CallFeedbackSummaryDeleter(target);
    }

    public static CallFeedbackSummaryFetcher fetch(final String sid) {
        return new CallFeedbackSummaryFetcher(sid);
    }

    public static CallFeedbackSummary fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, CallFeedbackSummary.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static CallFeedbackSummary fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, CallFeedbackSummary.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public final Boolean getIncludeSubaccounts() {
        return includeSubaccounts;
    }

    public final CallFeedbackSummary.Status getStatus() {
        return status;
    }

    public final Double getQualityScoreStandardDeviation() {
        return qualityScoreStandardDeviation;
    }

    public final LocalDate getEndDate() {
        return endDate;
    }

    public final Integer getCallFeedbackCount() {
        return callFeedbackCount;
    }

    public final DateTime getDateUpdated() {
        return dateUpdated;
    }

    public final List<CallFeedbackSummaryIssue> getIssues() {
        return issues;
    }

    public final String getAccountSid() {
        return accountSid;
    }

    public final Double getQualityScoreMedian() {
        return qualityScoreMedian;
    }

    public final Double getQualityScoreAverage() {
        return qualityScoreAverage;
    }

    public final String getSid() {
        return sid;
    }

    public final DateTime getDateCreated() {
        return dateCreated;
    }

    public final LocalDate getStartDate() {
        return startDate;
    }

    public final Integer getCallCount() {
        return callCount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CallFeedbackSummary self = (CallFeedbackSummary) o;

        return (Objects.equals(includeSubaccounts, self.includeSubaccounts) &&
                Objects.equals(status, self.status) &&
                Objects.equals(qualityScoreStandardDeviation, self.qualityScoreStandardDeviation) &&
                Objects.equals(endDate, self.endDate) &&
                Objects.equals(callFeedbackCount, self.callFeedbackCount) &&
                Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(issues, self.issues) &&
                Objects.equals(accountSid, self.accountSid) &&
                Objects.equals(qualityScoreMedian, self.qualityScoreMedian) &&
                Objects.equals(qualityScoreAverage, self.qualityScoreAverage) &&
                Objects.equals(sid, self.sid) &&
                Objects.equals(dateCreated, self.dateCreated) &&
                Objects.equals(startDate, self.startDate) &&
                Objects.equals(callCount, self.callCount));
    }

    @Override
    public int hashCode() {
        return Objects.hash(includeSubaccounts, status, qualityScoreStandardDeviation, endDate, callFeedbackCount,
                            dateUpdated, issues, accountSid, qualityScoreMedian, qualityScoreAverage, sid, dateCreated,
                            startDate, callCount);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("includeSubaccounts", includeSubaccounts)
                          .add("status", status)
                          .add("qualityScoreStandardDeviation", qualityScoreStandardDeviation)
                          .add("endDate", endDate)
                          .add("callFeedbackCount", callFeedbackCount)
                          .add("dateUpdated", dateUpdated)
                          .add("issues", issues)
                          .add("accountSid", accountSid)
                          .add("qualityScoreMedian", qualityScoreMedian)
                          .add("qualityScoreAverage", qualityScoreAverage)
                          .add("sid", sid)
                          .add("dateCreated", dateCreated)
                          .add("startDate", startDate)
                          .add("callCount", callCount)
                          .toString();
    }

    public enum Status {
        QUEUED("queued"), IN_PROGRESS("in-progress"), COMPLETED("completed"), FAILED("failed");
        private final String status;

        private Status(final String status) {
            this.status = status;
        }

        public String toString() {
            return status;
        }

        @JsonCreator
        public static Status forValue(final String value) {
            String munged = value.replace("-", "_")
                                 .toUpperCase();
            return Status.valueOf(munged);
        }
    }

}
