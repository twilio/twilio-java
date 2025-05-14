package com.twilio.taskrouter;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Workflow}
 */
public class WorkflowTest {

    @Test
    public void testToJson() throws IOException {
        List<WorkflowRuleTarget> targets = Collections.singletonList(
            new WorkflowRuleTarget.Builder("WQaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                .priority(54333)
                .timeout(30)
                .build()
        );
        List<WorkflowRule> rules = Collections.singletonList(
            new WorkflowRule.Builder("skill == \"HR\"", targets).friendlyName("4354").build()
        );
        WorkflowRuleTarget defaultTarget = new WorkflowRuleTarget.Builder("WQaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").build();
        Workflow workflow = new Workflow(rules, defaultTarget);

        assertEquals("{\"task_routing\":{\"filters\":[{\"expression\":\"skill == \\\"HR\\\"\",\"targets\":[{\"queue\":\"WQaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"priority\":54333,\"timeout\":30}],\"friendlyName\":\"4354\"}],\"default_filter\":{\"queue\":\"WQaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}}}", workflow.toJson());
    }

    @Test
    public void testFromJson() throws IOException {
        String json = "{\n" +
            "    \"task_routing\": {\n" +
            "        \"filters\": [{\n" +
            "            \"expression\": \"skill == \\\"HR\\\"\",\n" +
            "            \"friendlyName\": \"4354\",\n" +
            "            \"targets\": [{\n" +
            "                \"queue\": \"WQaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\n" +
            "                \"priority\": 54333,\n" +
            "                \"timeout\": 30\n" +
            "            }]\n" +
            "        }],\n" +
            "        \"default_filter\": {\n" +
            "            \"queue\": \"WQaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

        Workflow workflow = Workflow.fromJson(json);

        WorkflowRuleTarget defaultTarget = workflow.getDefaultTarget();
        assertEquals("WQaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", defaultTarget.getQueue());

        List<WorkflowRule> rules = workflow.getWorkflowRules();
        assertEquals(1, rules.size());

        WorkflowRule rule = rules.get(0);
        assertEquals("skill == \"HR\"", rule.getExpression());
        assertEquals("4354", rule.getFriendlyName());

        List<WorkflowRuleTarget> targets = rule.getWorkflowRuleTargets();
        assertEquals(1, targets.size());

        WorkflowRuleTarget target = targets.get(0);
        assertEquals("WQaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", target.getQueue());
        assertEquals(54333, (int) target.getPriority());
        assertEquals(30, (int) target.getTimeout());
    }

}
