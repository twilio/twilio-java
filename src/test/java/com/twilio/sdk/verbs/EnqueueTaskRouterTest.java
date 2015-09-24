package com.twilio.sdk.verbs;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class EnqueueTaskRouterTest {

    @Test
    /**
     * Test Enqueuing a Task with a json string
     */
    public void testEnqueueTask() throws TwiMLException {
        Enqueue enq = new Enqueue();
        enq.setWorkflowSid("WFxxxx");

        String json = "{\"selected_language\":\"en\"}";
        Task task = new Task(json);
        enq.append(task);

        assertEquals("<Enqueue workflowSid=\"WFxxxx\"><Task>{\"selected_language\":\"en\"}</Task></Enqueue>", enq.toXML());
    }

    @Test
    /**
     * Test Enqueuing a Task with a map that we'll convert to a json string
     */
    public void testEnqueueTaskWithMap() throws TwiMLException {
        Enqueue enq = new Enqueue();
        enq.setWorkflowSid("WFxxxx");

        Map<String, String> params = new HashMap<String, String>();
        params.put("selected_language", "en");

        Task task = new Task(params);
        enq.append(task);

        assertEquals("<Enqueue workflowSid=\"WFxxxx\"><Task>{\"selected_language\":\"en\"}</Task></Enqueue>", enq.toXML());
    }

    @Test
    /**
     * Test Enqueuing a Task without any attributes
     */
    public void testEnqueueTaskWithNoAttributes() throws TwiMLException {
        Enqueue enq = new Enqueue();
        enq.setWorkflowSid("WFxxxx");

        Task task = new Task("");
        enq.append(task);

        assertEquals("<Enqueue workflowSid=\"WFxxxx\"><Task></Task></Enqueue>", enq.toXML());
    }

    @Test
    /**
     * Test Enqueuing a Task with a dynamic priority
     */
    public void testEnqueueTaskWithPriority() throws TwiMLException {
        Enqueue enq = new Enqueue();
        enq.setWorkflowSid("WFxxxx");

        Task task = new Task("");
        task.setPriority(10);
        enq.append(task);

        assertEquals("<Enqueue workflowSid=\"WFxxxx\"><Task priority=\"10\"></Task></Enqueue>", enq.toXML());
    }

    @Test
    /**
     * Test Enqueuing a Task with a dynamic timeout
     */
    public void testEnqueueTaskWithTimeout() throws TwiMLException {
        Enqueue enq = new Enqueue();
        enq.setWorkflowSid("WFxxxx");

        Task task = new Task("");
        task.setTimeout(30);
        enq.append(task);

        assertEquals("<Enqueue workflowSid=\"WFxxxx\"><Task timeout=\"30\"></Task></Enqueue>", enq.toXML());
    }

    @Test
    /**
     * Test Enqueuing a Task with json attributes, dynamic priority and timeout
     */
    public void testEnqueueTaskWithAllAttributes() throws TwiMLException {
        Enqueue enq = new Enqueue();
        enq.setWorkflowSid("WFxxxx");

        Map<String, String> params = new HashMap<String, String>();
        params.put("selected_language", "en");

        Task task = new Task(params);
        task.setPriority(10);
        task.setTimeout(30);
        enq.append(task);

        assertEquals("<Enqueue workflowSid=\"WFxxxx\"><Task priority=\"10\" timeout=\"30\">{\"selected_language\":\"en\"}</Task></Enqueue>", enq.toXML());
    }
    
    @Test
    /**
     * Test Enqueuing a Task with different constructor with empty queue name and empty json
     */
    public void testEnqueueTaskEmptyQueue() throws TwiMLException {
        Enqueue enq = new Enqueue("");
        enq.setWorkflowSid("WFxxxx");

        String json = "{}";
        Task task = new Task(json);
        enq.append(task);

        assertEquals("<Enqueue workflowSid=\"WFxxxx\"><Task>{}</Task></Enqueue>", enq.toXML());
    }
    
    @Test
    /**
     * Test Enqueuing a Task with different constructor with null queue name and empty json
     */
    public void testEnqueueTaskNullQueue() throws TwiMLException {
        Enqueue enq = new Enqueue(null);
        enq.setWorkflowSid("WFxxxx");

        String json = "{}";
        Task task = new Task(json);
        enq.append(task);

        assertEquals("<Enqueue workflowSid=\"WFxxxx\"><Task>{}</Task></Enqueue>", enq.toXML());
    }
    
    @Test
    /**
     * Test Enqueuing a Task with different constructor with n/a queue name and empty json
     */
    public void testEnqueueTaskRandomInvalidQueue() throws TwiMLException {
        Enqueue enq = new Enqueue("NotUsedQueue");
        enq.setWorkflowSid("WFxxxx");

        String json = "{}";
        Task task = new Task(json);
        enq.append(task);

        assertEquals("<Enqueue workflowSid=\"WFxxxx\">NotUsedQueue<Task>{}</Task></Enqueue>", enq.toXML());
    }

}
