package com.twilio.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

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
        assertEquals(5, issue.getCount());
        assertEquals("issue", issue.getDescription());
        assertEquals("99.9", issue.getPercentageOfTotalCalls());
    }

}
