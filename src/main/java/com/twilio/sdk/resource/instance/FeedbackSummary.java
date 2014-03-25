package com.twilio.sdk.resource.instance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.InstanceResource;

/**
 * The Feedback Summary class contains a summary of the 
 * {@link com.twilio.sdk.resource.instance.Feedback} submitted for a type of 
 * resource, Calls for example. 
 */
public abstract class FeedbackSummary extends InstanceResource {
    
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    
    // Feedback Summary Properties
    public static final String START_DATE_PROP = "start_date";
    public static final String END_DATE_PROP = "end_date";
    public static final String ACCOUNT_SID_PROP = "account_sid";
    public static final String QUALITY_SCORE_AVERAGE_PROP = "quality_score_average";
    public static final String QUALITY_SCORE_MEDIAN_PROP = "quality_score_median";
    public static final String QUALITY_SCORE_STDDEV_PROP = "quality_score_standard_deviation";
    public static final String ISSUES_PROP = "issues";
    
    // Feedback Summary Parameters
    public static final String START_DATE_PARAM = "StartDate";
    public static final String END_DATE_PARAM = "EndDate";
    public static final String INCLUDE_SUBACCOUNTS_PARAM = "IncludeSubaccounts";
    
    private String parentLocation;
    
    /**
     * Instantiates a new feedback summary.
     *
     * @param client the client
     * @param properties the properties
     */
    public FeedbackSummary(TwilioRestClient client, Map<String, Object> properties) {
        super(client, properties);
    }
    
    /**
     * Instantiates a new feedback summary.
     *
     * @param client the client
     * @param parentLocation the parent location
     */
    public FeedbackSummary(TwilioRestClient client, String parentLocation) {
        super(client);
        this.parentLocation = parentLocation;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected String getResourceLocation() {
        return this.parentLocation + "/Feedback/Summary.json";
    }
    
    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        try {
            return format.parse(this.getProperty(START_DATE_PROP));
        } catch (ParseException e) {
            return null;
        }
    }
    
    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        try {
            return format.parse(this.getProperty(END_DATE_PROP));
        } catch (ParseException e) {
            return null;
        }
    }
    
    /**
     * Gets the account sid.
     *
     * @return the account sid
     */
    public String getAccountSid() {
        return this.getProperty(ACCOUNT_SID_PROP);
    }
    
    /**
     * Gets the quality score average.
     *
     * @return the quality score average
     */
    public double getQualityScoreAverage() {
        try {
            return Double.parseDouble(this.getProperty(QUALITY_SCORE_AVERAGE_PROP));
        } catch(NumberFormatException nfe) {
            throw new IllegalStateException("The Feedback Summary doesn't have a quality score average property set.", nfe);
        }
    }
    
    /**
     * Gets the quality score median.
     *
     * @return the quality score median
     */
    public double getQualityScoreMedian() {
        try {
            return Double.parseDouble(this.getProperty(QUALITY_SCORE_MEDIAN_PROP));
        } catch(NumberFormatException nfe) {
            throw new IllegalStateException("The Feedback Summary doesn't have a quality score median property set.", nfe);
        }
    }
    
    /**
     * Gets the quality score standard deviation.
     *
     * @return the quality score standard deviation
     */
    public double getQualityScoreStandardDeviation() {
        try {
            return Double.parseDouble(this.getProperty(QUALITY_SCORE_STDDEV_PROP));
        } catch(NumberFormatException nfe) {
            throw new IllegalStateException("The Feedback Summary doesn't have a quality score standard deviation property set.", nfe);
        }
    }
    
    /**
     * Concrete classes must implement this class to convert a map to an 
     * {@link com.twilio.sdk.resource.instance.FeedbackSummary.IssueSummary} class.
     *
     * @param data the data
     * @return the issue summary
     */
    protected abstract IssueSummary mapToIssueSummary(Map<String, Object> data);
    
    /**
     * Gets the parent object's issues.
     *
     * @return the issues
     */
    public Set<IssueSummary> getIssues() {
        List<Map<String, Object>> props = (List<Map<String, Object>>) this.getObject(ISSUES_PROP);
        
        if (props != null) {
            Set<IssueSummary> issues = new HashSet<IssueSummary>();
            
            for(Map<String, Object> prop: props) {
                try {
                    IssueSummary issueSummary = mapToIssueSummary(prop);
                    issues.add(issueSummary);
                } catch(NumberFormatException nfe) {
                    throw new IllegalStateException("The Feedback Summary issues field contained improperly formatted data.", nfe);
                }
            }
            
            return Collections.unmodifiableSet(issues);
        }
        
        throw new IllegalStateException("The Feedback Summary doesn't have the issues property set.");
    }
    
    /**
     * The Class IssueSummary.
     */
    class IssueSummary {    
        
        private String description;
        private int count;
        private double percentage;
        
        /**
         * Instantiates a new issue summary.
         *
         * @param description the description
         * @param count the count
         * @param percentage the percentage
         */
        protected IssueSummary(String description, int count, double percentage) {
            this.description = description;
            this.count = count;
            this.percentage = percentage;
        }
        
        /**
         * Gets the description.
         *
         * @return the description
         */
        public String getDescription() {
            return this.description;
        }
        
        /**
         * Gets the count.
         *
         * @return the count
         */
        public int getCount() {
            return this.count;
        }
        
        /**
         * Gets the percentage.
         *
         * @return the percentage
         */
        public double getPercentage() {
            return this.percentage;
        }

        /**
         * Gets the outer type.
         *
         * @return the outer type
         */
        private FeedbackSummary getOuterType() {
            return FeedbackSummary.this;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            
            result = prime * result + getOuterType().hashCode();
            result = prime * result + count;
            result = prime * result + ((description == null) ? 0 : description.hashCode());
            
            long temp;
            temp = Double.doubleToLongBits(percentage);
            result = prime * result + (int) (temp ^ (temp >>> 32));
            
            return result;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            
            if (obj == null)
                return false;
            
            if (getClass() != obj.getClass())
                return false;
            
            IssueSummary other = (IssueSummary) obj;
            
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            
            if (count != other.count)
                return false;
            
            if ((description == null)&&(other.description != null)) {
                    return false;
            } 
            
            else if (!description.equals(other.description))
                return false;
            
            if (Double.doubleToLongBits(percentage) != Double.doubleToLongBits(other.percentage))
                return false;
            
            return true;
        }       
        
    }
}
