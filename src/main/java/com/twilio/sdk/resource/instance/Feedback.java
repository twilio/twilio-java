package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.InstanceResource;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A Feedback class. Used to submit feedback for a resource.
 */
public class Feedback extends InstanceResource<TwilioRestClient> {

    public static final String QUALITY_SCORE_PROP = "quality_score";
    public static final String ISSUES_PROP = "issues";
    public static final String DATE_CREATED_PROP = "date_created";
    public static final String DATE_UPDATED_PROP = "date_updated";
    public static final String QUALITY_SCORE_PARAM = "QualityScore";
    public static final String ISSUE_PARAM = "Issue";

    private String parentLocation;

    /**
     * Instantiates a new feedback.
     *
     * @param client the client
     * @param properties the properties
     * @param parentLocation the parent location
     */
    public Feedback(TwilioRestClient client, Map<String, Object> properties, String parentLocation) {
        super(client, properties);
        this.parentLocation = parentLocation;
    }

    /**
     * Instantiates a new feedback.
     *
     * @param client the client
     * @param parentLocation the parent location
     */
    public Feedback(TwilioRestClient client, String parentLocation) {
        super(client);
        this.parentLocation = parentLocation;
    }

    /**
     * Delete the call feedback.
     *
     * @return true, if successful
     * @throws TwilioRestException if there is an error in the request
     */
    public boolean delete() throws TwilioRestException {
        TwilioRestResponse response = this.getClient().safeRequest(
                this.getResourceLocation(), "DELETE", (Map) null);

        return !response.isError();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getResourceLocation() {
        return this.parentLocation + "/Feedback.json";
    }

    /**
     * Gets the quality score.
     *
     * @return the quality score
     */
    public int getQualityScore() {
        Integer prop = (Integer) this.getObject(QUALITY_SCORE_PROP);

        if (prop != null) {
            return prop;
        }

        throw new IllegalStateException("The Feedback instance doesn't have the quality score property set.");
    }

    /**
     * Gets the issues.
     *
     * @return the issues
     */
    public Set<String> getIssues() {
        List<String> props = (List<String>) this.getObject(ISSUES_PROP);

        if (props != null) {
            Set<String> issues = new HashSet<String>(props);
            return Collections.unmodifiableSet(issues);
        }

        return null;
    }

    /**
     * Gets the date created.
     *
     * @return the date created
     */
    public Date getDateCreated() {
        return parseDate(this.getProperty(DATE_CREATED_PROP));
    }

    /**
     * Gets the date updated.
     *
     * @return the date updated
     */
    public Date getDateUpdated() {
        return parseDate(this.getProperty(DATE_UPDATED_PROP));
    }
}
