package com.twilio.sdk.resource.instance;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;

/**
 * The Call Feedback Summary class encapsulates a summary of the 
 * {@link com.twilio.sdk.resource.instance.Feedback} submitted for all calls
 * for this requesting account.
 */
public class CallFeedbackSummary extends FeedbackSummary{

    public static final String CALL_COUNT_PROP = "call_count";
    public static final String CALL_FEEDBACK_COUNT_PROP = "call_feedback_count";
    public static final String CALL_ISSUE_DESCRIPTION_PROP = "description";
    public static final String CALL_ISSUE_COUNT_PROP = "count";
    public static final String CALL_ISSUE_PERCENTAGE_PROP = "percentage_of_total_calls";
    
    /**
     * Instantiates a new call feedback summary.
     *
     * @param client the client
     * @param properties the properties
     */
    public CallFeedbackSummary(TwilioRestClient client, Map<String, Object> properties) {
        super(client, properties);
    }
    
    /**
     * Instantiates a new call feedback summary.
     *
     * @param client the client
     * @param parentLocation the parent location
     */
    public CallFeedbackSummary(TwilioRestClient client, String parentLocation) {
        super(client, parentLocation);
    }
 
    /**
     * Gets the call count.
     *
     * @return the call count
     * @throws IllegalStateException
     */
    public int getCallCount() {
        try {
            return (Integer) this.getObject(CALL_COUNT_PROP);
        } catch(Exception e) {
            throw new IllegalStateException("The Feedback Summary call count property contained improperly formatted data.", e);
        }
    }
    
    /**
     * Gets the call feedback count.
     *
     * @return the call feedback count
     * @throws IllegalStateException
     */
    public int getCallFeedbackCount() {
        try {
            return (Integer) this.getObject(CALL_FEEDBACK_COUNT_PROP);
        } catch(NumberFormatException e) {
            throw new IllegalStateException("The Feedback Summary call feedback count property contained improperly formatted data.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IssueSummary mapToIssueSummary(Map<String, Object> data) {
        String description;
        int count;
        double percentage;
        
        try {
            description = (String)data.get(CALL_ISSUE_DESCRIPTION_PROP);
            count = (Integer) data.get(CALL_ISSUE_COUNT_PROP);
            percentage = Double.parseDouble(((String)data.get(CALL_ISSUE_PERCENTAGE_PROP)).replace("%", ""));
        } catch(Exception nfe) {
            throw new IllegalStateException("A Feedback Issue Summary contained improperly formatted data.", nfe);
        }
        
        return new IssueSummary(description, count, percentage);
    }
    
}
