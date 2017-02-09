package com.twilio.type;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Test class for {@link FeedbackIssue}.
 */
public class FeedbackIssueTest extends TypeTest {

    @Test
    public void testFromJson() throws IOException {
        String json = "{\n" +
            "    \"count\": 5,\n" +
            "    \"description\": \"issue\",\n" +
            "    \"percentage_of_total_calls\": \"99.9\"\n" +
            "}";

        FeedbackIssue issue = fromJson(json, FeedbackIssue.class);
        Assert.assertEquals(5, issue.getCount());
        Assert.assertEquals("issue", issue.getDescription());
        Assert.assertEquals("99.9", issue.getPercentageOfTotalCalls());
    }

}
