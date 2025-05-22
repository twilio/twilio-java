package com.twilio.taskrouter;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link WorkflowRuleTarget}
 */
public class WorkflowRuleTargetTest {

    @Test
    public void testToJson() throws IOException {
        WorkflowRuleTarget target = new WorkflowRuleTarget.Builder("QS123")
            .expression("1==1")
            .priority(5)
            .timeout(30)
            .orderBy("worker.english_level ASC")
            .skipIf("workers.available == 0")
            .knownWorkerSid("task.requested_agent_id")
            .knownWorkerFriendlyName("task.requested_agent")
            .build();

        assertEquals(
            "{\"queue\":\"QS123\",\"expression\":\"1==1\",\"priority\":5,\"timeout\":30,\"order_by\":\"worker.english_level ASC\",\"skip_if\":\"workers.available == 0\",\"known_worker_sid\":\"task.requested_agent_id\",\"known_worker_friendly_name\":\"task.requested_agent\"}",
            target.toJson()
        );
    }

    @Test
    public void testFromJson() throws IOException {
        WorkflowRuleTarget target =
            WorkflowRuleTarget.fromJson("{\"queue\":\"QS123\",\"expression\":\"1==1\",\"priority\":5,\"timeout\":30,\"order_by\":\"worker.english_level ASC\",\"skip_if\":\"workers.available == 0\",\"known_worker_friendly_name\":\"task.requested_agent\",\"known_worker_sid\":\"task.requested_agent_id\"}");

        assertEquals("QS123", target.getQueue());
        assertEquals("1==1", target.getExpression());
        assertEquals(5, (int)target.getPriority());
        assertEquals(30, (int)target.getTimeout());
        assertEquals("worker.english_level ASC", target.getOrderBy());
        assertEquals("workers.available == 0", target.getSkipIf());
        assertEquals("task.requested_agent", target.getKnownWorkerFriendlyName());
        assertEquals("task.requested_agent_id", target.getKnownWorkerSid());
    }
}
