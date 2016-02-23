package com.twilio.sdk.resources.api.v2010.account.call;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.creators.api.v2010.account.call.FeedbackSummaryCreator;
import com.twilio.sdk.deleters.api.v2010.account.call.FeedbackSummaryDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.api.v2010.account.call.FeedbackSummaryFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.types.FeedbackIssue;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedbackSummary extends SidResource {
    private static final long serialVersionUID = 167286727474638L;

    public enum Status {
        QUEUED("queued"),
        IN_PROGRESS("in-progress"),
        COMPLETED("completed"),
        FAILED("failed");
    
        private final String value;
        
        private Status(final String value) {
            this.value = value;
        }
        
        public String toString() {
            return value;
        }
        
        @JsonCreator
        public static Status forValue(final String value) {
            String normalized = value.replace("-", "_").toUpperCase();
            return Status.valueOf(normalized);
        }
    }

    /**
     * Create a FeedbackSummaryCreator to execute create.
     * 
     * @param accountSid The account_sid
     * @param startDate The start_date
     * @param endDate The end_date
     * @return FeedbackSummaryCreator capable of executing the create
     */
    public static FeedbackSummaryCreator create(final String accountSid, 
                                                final LocalDate startDate, 
                                                final LocalDate endDate) {
        return new FeedbackSummaryCreator(accountSid, startDate, endDate);
    }

    /**
     * Create a FeedbackSummaryFetcher to execute fetch.
     * 
     * @param accountSid The account_sid
     * @param sid The sid
     * @return FeedbackSummaryFetcher capable of executing the fetch
     */
    public static FeedbackSummaryFetcher fetch(final String accountSid, 
                                               final String sid) {
        return new FeedbackSummaryFetcher(accountSid, sid);
    }

    /**
     * Create a FeedbackSummaryDeleter to execute delete.
     * 
     * @param accountSid The account_sid
     * @param sid The sid
     * @return FeedbackSummaryDeleter capable of executing the delete
     */
    public static FeedbackSummaryDeleter delete(final String accountSid, 
                                                final String sid) {
        return new FeedbackSummaryDeleter(accountSid, sid);
    }

    /**
     * Converts a JSON String into a FeedbackSummary object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return FeedbackSummary object represented by the provided JSON
     */
    public static FeedbackSummary fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, FeedbackSummary.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a FeedbackSummary object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return FeedbackSummary object represented by the provided JSON
     */
    public static FeedbackSummary fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, FeedbackSummary.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final Integer callCount;
    private final Integer callFeedbackCount;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final DateTime endDate;
    private final Boolean includeSubaccounts;
    private final List<FeedbackIssue> issues;
    private final BigDecimal qualityScoreAverage;
    private final BigDecimal qualityScoreMedian;
    private final BigDecimal qualityScoreStandardDeviation;
    private final String sid;
    private final DateTime startDate;
    private final FeedbackSummary.Status status;

    @JsonCreator
    private FeedbackSummary(@JsonProperty("account_sid")
                            final String accountSid, 
                            @JsonProperty("call_count")
                            final Integer callCount, 
                            @JsonProperty("call_feedback_count")
                            final Integer callFeedbackCount, 
                            @JsonProperty("date_created")
                            final String dateCreated, 
                            @JsonProperty("date_updated")
                            final String dateUpdated, 
                            @JsonProperty("end_date")
                            final String endDate, 
                            @JsonProperty("include_subaccounts")
                            final Boolean includeSubaccounts, 
                            @JsonProperty("issues")
                            final List<FeedbackIssue> issues, 
                            @JsonProperty("quality_score_average")
                            final BigDecimal qualityScoreAverage, 
                            @JsonProperty("quality_score_median")
                            final BigDecimal qualityScoreMedian, 
                            @JsonProperty("quality_score_standard_deviation")
                            final BigDecimal qualityScoreStandardDeviation, 
                            @JsonProperty("sid")
                            final String sid, 
                            @JsonProperty("start_date")
                            final String startDate, 
                            @JsonProperty("status")
                            final FeedbackSummary.Status status) {
        this.accountSid = accountSid;
        this.callCount = callCount;
        this.callFeedbackCount = callFeedbackCount;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.endDate = MarshalConverter.dateTimeFromString(endDate);
        this.includeSubaccounts = includeSubaccounts;
        this.issues = issues;
        this.qualityScoreAverage = qualityScoreAverage;
        this.qualityScoreMedian = qualityScoreMedian;
        this.qualityScoreStandardDeviation = qualityScoreStandardDeviation;
        this.sid = sid;
        this.startDate = MarshalConverter.dateTimeFromString(startDate);
        this.status = status;
    }

    /**
     * Returns The The account_sid.
     * 
     * @return The account_sid
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The The call_count.
     * 
     * @return The call_count
     */
    public final Integer getCallCount() {
        return this.callCount;
    }

    /**
     * Returns The The call_feedback_count.
     * 
     * @return The call_feedback_count
     */
    public final Integer getCallFeedbackCount() {
        return this.callFeedbackCount;
    }

    /**
     * Returns The The date_created.
     * 
     * @return The date_created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The The date_updated.
     * 
     * @return The date_updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The The end_date.
     * 
     * @return The end_date
     */
    public final DateTime getEndDate() {
        return this.endDate;
    }

    /**
     * Returns The The include_subaccounts.
     * 
     * @return The include_subaccounts
     */
    public final Boolean getIncludeSubaccounts() {
        return this.includeSubaccounts;
    }

    /**
     * Returns The The issues.
     * 
     * @return The issues
     */
    public final List<FeedbackIssue> getIssues() {
        return this.issues;
    }

    /**
     * Returns The The quality_score_average.
     * 
     * @return The quality_score_average
     */
    public final BigDecimal getQualityScoreAverage() {
        return this.qualityScoreAverage;
    }

    /**
     * Returns The The quality_score_median.
     * 
     * @return The quality_score_median
     */
    public final BigDecimal getQualityScoreMedian() {
        return this.qualityScoreMedian;
    }

    /**
     * Returns The The quality_score_standard_deviation.
     * 
     * @return The quality_score_standard_deviation
     */
    public final BigDecimal getQualityScoreStandardDeviation() {
        return this.qualityScoreStandardDeviation;
    }

    /**
     * Returns The The sid.
     * 
     * @return The sid
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The The start_date.
     * 
     * @return The start_date
     */
    public final DateTime getStartDate() {
        return this.startDate;
    }

    /**
     * Returns The The status.
     * 
     * @return The status
     */
    public final FeedbackSummary.Status getStatus() {
        return this.status;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        FeedbackSummary other = (FeedbackSummary) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(callCount, other.callCount) && 
               Objects.equals(callFeedbackCount, other.callFeedbackCount) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(endDate, other.endDate) && 
               Objects.equals(includeSubaccounts, other.includeSubaccounts) && 
               Objects.equals(issues, other.issues) && 
               Objects.equals(qualityScoreAverage, other.qualityScoreAverage) && 
               Objects.equals(qualityScoreMedian, other.qualityScoreMedian) && 
               Objects.equals(qualityScoreStandardDeviation, other.qualityScoreStandardDeviation) && 
               Objects.equals(sid, other.sid) && 
               Objects.equals(startDate, other.startDate) && 
               Objects.equals(status, other.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            callCount,
                            callFeedbackCount,
                            dateCreated,
                            dateUpdated,
                            endDate,
                            includeSubaccounts,
                            issues,
                            qualityScoreAverage,
                            qualityScoreMedian,
                            qualityScoreStandardDeviation,
                            sid,
                            startDate,
                            status);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("callCount", callCount)
                          .add("callFeedbackCount", callFeedbackCount)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("endDate", endDate)
                          .add("includeSubaccounts", includeSubaccounts)
                          .add("issues", issues)
                          .add("qualityScoreAverage", qualityScoreAverage)
                          .add("qualityScoreMedian", qualityScoreMedian)
                          .add("qualityScoreStandardDeviation", qualityScoreStandardDeviation)
                          .add("sid", sid)
                          .add("startDate", startDate)
                          .add("status", status)
                          .toString();
    }
}