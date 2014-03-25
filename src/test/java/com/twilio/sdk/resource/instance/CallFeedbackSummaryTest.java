package com.twilio.sdk.resource.instance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.twilio.sdk.resource.instance.FeedbackSummary.IssueSummary;

public class CallFeedbackSummaryTest extends BasicRequestTester {
    
    @Mock
    private Account account;
  
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
  
    private CallFeedbackSummary feedbackSummary;
    
    @Before
    public void setup() throws Exception {
        setExpectedServerContentType("application/json");
        setExpectedServerAnswer("callfeedbacksummary.json");
        
        Map<String, String> filters = new HashMap<String, String>();
        filters.put("StartDate", "2014-01-01");
        filters.put("EndDate", "2014-01-31");
        
        feedbackSummary = client.getAccount().getCallFeedbackSummary(filters);
    }
    
    @Test
    public void testGetCallFeedbackSummary() {
        assertNotNull(feedbackSummary);
    }
    
    @Test
    public void testGetStartDate() throws ParseException {
        assertEquals(format.parse("2014-01-01"), feedbackSummary.getStartDate());
    }
 
    @Test
    public void testGetEndDate() throws ParseException {
        assertEquals(format.parse("2014-01-31"), feedbackSummary.getEndDate());
    }

    @Test
    public void testGetAcountSid() {
        assertEquals(accountSid, feedbackSummary.getAccountSid());
    }
   
    @Test
    public void testGetCallCount() throws ParseException {
        assertEquals(10200, feedbackSummary.getCallCount());
    }
   
    @Test
    public void testGetCallFeedbackCount() throws ParseException {
        assertEquals(729, feedbackSummary.getCallFeedbackCount());
    }
    
    
    @Test
    public void testGetQualityScoreAverage() throws ParseException {
        assertEquals(4.5, feedbackSummary.getQualityScoreAverage(), 0.001);
    }
    
    @Test
    public void testGetQualityScoreMedian() throws ParseException {
        assertEquals(4.0, feedbackSummary.getQualityScoreMedian(), 0.001);
    }
    
    @Test
    public void testGetQualityScoreStandardDeviation() throws ParseException {
        assertEquals(1.0, feedbackSummary.getQualityScoreStandardDeviation(), 0.001);
    }
    
    @Test
    public void testGetIssue() {
        Set<IssueSummary> issues = new HashSet<IssueSummary>();
        IssueSummary issue = feedbackSummary.new IssueSummary("imperfect-audio", 45, 0.04);
        issues.add(issue);
        
        assertEquals(issues, feedbackSummary.getIssues());
    }
}
