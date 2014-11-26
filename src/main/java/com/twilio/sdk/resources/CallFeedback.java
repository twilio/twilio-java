package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.creators.CallFeedbackCreator;
import com.twilio.sdk.deleters.CallFeedbackDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.CallFeedbackFetcher;
import com.twilio.sdk.updaters.CallFeedbackUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CallFeedback extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final DateTime dateUpdated;
    private final int qualityScore;
    private final String accountSid;
    private final String sid;
    private final DateTime dateCreated;
    private final List<CallFeedback.Issue> issues;

    @JsonCreator
    private CallFeedback(@JsonProperty("date_updated") final String dateUpdated,
                         @JsonProperty("quality_score") final int qualityScore,
                         @JsonProperty("account_sid") final String accountSid, @JsonProperty("sid") final String sid,
                         @JsonProperty("date_created") final String dateCreated,
                         @JsonProperty("issues") final List<CallFeedback.Issue> issues) {
        this.dateUpdated = DateTime.parse(dateUpdated, Twilio.DATE_TIME_FORMATTER);
        this.qualityScore = qualityScore;
        this.accountSid = accountSid;
        this.sid = sid;
        this.dateCreated = DateTime.parse(dateCreated, Twilio.DATE_TIME_FORMATTER);
        this.issues = issues;

    }

    public static CallFeedbackCreator create(final String callSid, final int qualityScore) {
        return new CallFeedbackCreator(callSid, qualityScore);
    }

    public static CallFeedbackDeleter delete(final String sid) {
        return new CallFeedbackDeleter(sid);
    }

    public static CallFeedbackDeleter delete(final CallFeedback target) {
        return new CallFeedbackDeleter(target);
    }

    public static CallFeedbackFetcher fetch(final String sid) {
        return new CallFeedbackFetcher(sid);
    }

    public static CallFeedbackUpdater update(final CallFeedback target) {
        return new CallFeedbackUpdater(target);
    }

    public static CallFeedbackUpdater update(final String sid, final int qualityScore) {
        return new CallFeedbackUpdater(sid, qualityScore);
    }

    public static CallFeedback fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, CallFeedback.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static CallFeedback fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, CallFeedback.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public final DateTime getDateUpdated() {
        return dateUpdated;
    }

    public final int getQualityScore() {
        return qualityScore;
    }

    public final String getAccountSid() {
        return accountSid;
    }

    public final String getSid() {
        return sid;
    }

    public final DateTime getDateCreated() {
        return dateCreated;
    }

    public final List<CallFeedback.Issue> getIssues() {
        return issues;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CallFeedback self = (CallFeedback) o;

        return (Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(qualityScore, self.qualityScore) &&
                Objects.equals(accountSid, self.accountSid) &&
                Objects.equals(sid, self.sid) &&
                Objects.equals(dateCreated, self.dateCreated) &&
                Objects.equals(issues, self.issues));
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateUpdated, qualityScore, accountSid, sid, dateCreated, issues);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("dateUpdated", dateUpdated)
                          .add("qualityScore", qualityScore)
                          .add("accountSid", accountSid)
                          .add("sid", sid)
                          .add("dateCreated", dateCreated)
                          .add("issues", issues)
                          .toString();
    }

    public enum Issue {
        IMPERFECT_AUDIO("imperfect-audio"), DROPPED_CALL("dropped-call"), INCORRECT_CALLER_ID(
                "incorrect-caller-id"), POST_DIAL_DELAY("post-dial-delay"), DIGITS_NOT_CAPTURED(
                "digits-not-captured"), UNSOLICITED_CALL("unsolicited-call"), AUDIO_LATENCY(
                "audio-latency"), ONE_WAY_AUDIO("one-way-audio");
        private final String issue;

        private Issue(final String issue) {
            this.issue = issue;
        }

        public String toString() {
            return issue;
        }

        @JsonCreator
        public static Issue forValue(final String value) {
            String munged = value.replace("-", "_")
                                 .toUpperCase();
            return Issue.valueOf(munged);
        }
    }
}
