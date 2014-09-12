package com.twilio.sdk.resource.instance;

import com.twilio.sdk.resource.factory.FeedbackFactory;
import com.twilio.sdk.resource.factory.impl.FeedbackFactoryImpl;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class FeedbackTest extends BasicRequestTester {

    public static final String CALL_SID = "AC123456";

    @Mock
    private Call call;

    private SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

    private Feedback feedback;

    @Before
    public void setup() throws Exception {
        when(call.getFeedbackFactory()).thenReturn(new FeedbackFactoryImpl(restClient, getResourceLocation()));

        FeedbackFactory factory = call.getFeedbackFactory();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("QualityScore", String.valueOf(3)));
        params.add(new BasicNameValuePair("Issue", "post-dial-delay"));
        params.add(new BasicNameValuePair("Issue", "digits-not-captured"));

        setExpectedServerContentType("application/json");
        setExpectedServerAnswer("callfeedback.json");
        setExpectedServerReturnCode(201);

        feedback = factory.create(params);
    }

    @Test
    public void testCreateFeedback() {
        assertNotNull(feedback);
    }

    @Test
    public void testDelete() throws Exception {
        setExpectedServerAnswer(null);
        setExpectedServerReturnCode(204);
        Feedback feedback = new Feedback(restClient, getResourceLocation());
        assertTrue(feedback.delete());
    }

    @Test
    public void testDeleteFeedback() throws Exception {
        setExpectedServerAnswer(null);
        setExpectedServerReturnCode(204);
        Call call = new Call(restClient, CALL_SID);
        assertTrue(call.deleteFeedback());
    }

    @Test
    public void testGetDateCreated() throws ParseException {
        assertEquals(format.parse("Tue, 06 Nov 2012 22:06:35 +0000"), feedback.getDateCreated());
    }

    @Test
    public void testGetDateUpdated() throws ParseException {
        assertEquals(format.parse("Tue, 07 Nov 2012 22:06:35 +0000"), feedback.getDateUpdated());
    }

    @Test
    public void testGetQualityScore() {
        assertEquals(3, feedback.getQualityScore());
    }

    @Test
    public void testGetIssues() {
        Set<String> expected = new HashSet<String>();
        expected.add("post-dial-delay");
        expected.add("digits-not-captured");

        assertEquals(expected, feedback.getIssues());
    }

    @Test
    public void testResourceLocation() {
        String parentLocation = getResourceLocation();

        assertEquals(parentLocation + "/Feedback.json", feedback.getResourceLocation());
    }

    private String getResourceLocation() {
        return "/2010-04-01/Accounts/" + accountSid + "/Calls/" + CALL_SID;
    }
}
