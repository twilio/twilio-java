package com.twilio.sdk.resource.instance;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class FeedbackSummaryTest extends BasicRequestTester {

	@Mock
	private Account account;

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	private FeedbackSummary feedbackSummaryCompleted;

	private FeedbackSummary feedbackSummaryQueued;

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("callfeedbacksummaryqueued.json");
		Map<String, String> filters = new HashMap<String, String>();
		filters.put("StartDate", "2014-01-01");
		filters.put("EndDate", "2014-01-31");
		feedbackSummaryQueued = restClient.getAccount().createFeedbackSummary(filters);
		setExpectedServerAnswer("callfeedbacksummarycompleted.json");
		feedbackSummaryCompleted = restClient.getAccount().getFeedbackSummary("FS0123456789abcdef0123456789abcdef");
	}

	@Test
	public void testGetCallFeedbackSummary() {
		assertNotNull(feedbackSummaryQueued);
		assertNotNull(feedbackSummaryCompleted);
	}

	@Test
	public void testGetStartDate() throws ParseException {
		assertEquals(format.parse("2014-01-01"), feedbackSummaryQueued.getStartDate());
		assertEquals(format.parse("2014-01-01"), feedbackSummaryCompleted.getStartDate());
	}

	@Test
	public void testGetEndDate() throws ParseException {
		assertEquals(format.parse("2014-01-31"), feedbackSummaryQueued.getEndDate());
		assertEquals(format.parse("2014-01-31"), feedbackSummaryCompleted.getEndDate());
	}

	@Test
	public void testGetAccountSid() {
		assertEquals(accountSid, feedbackSummaryQueued.getAccountSid());
		assertEquals(accountSid, feedbackSummaryCompleted.getAccountSid());
	}

	@Test
	public void testGetCallCount() throws ParseException {
		assertNull(feedbackSummaryQueued.getCallCount());
		assertEquals(10200, feedbackSummaryCompleted.getCallCount().intValue());
	}

	@Test
	public void testGetCallFeedbackCount() throws ParseException {
		assertNull(feedbackSummaryQueued.getCallFeedbackCount());
		assertEquals(729, feedbackSummaryCompleted.getCallFeedbackCount().intValue());
	}

	@Test
	public void testGetQualityScoreAverage() throws ParseException {
		assertNull(feedbackSummaryQueued.getQualityScoreAverage());
		assertEquals(4.5, feedbackSummaryCompleted.getQualityScoreAverage(), 0.001);
	}

	@Test
	public void testGetQualityScoreMedian() throws ParseException {
		assertNull(feedbackSummaryQueued.getQualityScoreMedian());
		assertEquals(4.0, feedbackSummaryCompleted.getQualityScoreMedian(), 0.001);
	}

	@Test
	public void testGetQualityScoreStandardDeviation() throws ParseException {
		assertNull(feedbackSummaryQueued.getQualityScoreStandardDeviation());
		assertEquals(1.0, feedbackSummaryCompleted.getQualityScoreStandardDeviation(), 0.001);
	}

	@Test
	public void testGetIssue() {
		Set<FeedbackSummaryIssue> issues = new HashSet<FeedbackSummaryIssue>();
		assertNull(feedbackSummaryQueued.getIssues());
		FeedbackSummaryIssue issue = new FeedbackSummaryIssue("imperfect-audio", 45, 0.04);
		issues.add(issue);
		assertEquals(issues, feedbackSummaryCompleted.getIssues());
	}
}
