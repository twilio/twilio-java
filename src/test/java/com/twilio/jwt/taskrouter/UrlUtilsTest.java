package com.twilio.jwt.taskrouter;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link UrlUtils}.
 */
public class UrlUtilsTest {

    private static final String WORKSPACE_SID = "WS123";
    private static final String WORKER_SID = "WK123";
    private static final String ACTIVITY_SID = "AC123";
    private static final String TASK_SID = "TK123";
    private static final String TASK_QUEUE_SID = "TQ123";
    private static final String RESERVATION_SID = "WR123";

    @Test
    public void testWorkspaces() {
        assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces",
            UrlUtils.workspaces()
        );
    }

    @Test
    public void testAllWorkspaces() {
        assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/**",
            UrlUtils.allWorkspaces()
        );
    }

    @Test
    public void testWorkspace() {
        assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123",
            UrlUtils.workspace(WORKSPACE_SID)
        );
    }

    @Test
    public void testTaskQueues() {
        assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/TaskQueues",
            UrlUtils.taskQueues(WORKSPACE_SID)
        );
    }

    @Test
    public void testAllTaskQueues() {
        assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/TaskQueues/**",
            UrlUtils.allTaskQueues(WORKSPACE_SID)
        );
    }

    @Test
    public void testTaskQueue() {
        assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/TaskQueues/TQ123",
            UrlUtils.taskQueue(WORKSPACE_SID, TASK_QUEUE_SID)
        );
    }

    @Test
    public void testTasks() {
        assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Tasks",
            UrlUtils.tasks(WORKSPACE_SID)
        );
    }

    @Test
    public void testAllTasks() {
        assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Tasks/**",
            UrlUtils.allTasks(WORKSPACE_SID)
        );
    }

    @Test
    public void testTask() {
        assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Tasks/TK123",
            UrlUtils.task(WORKSPACE_SID, TASK_SID)
        );
    }

    @Test
    public void testActivities() {
        assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Activities",
            UrlUtils.activities(WORKSPACE_SID)
        );
    }

    @Test
    public void testAllActivities() {
        assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Activities/**",
            UrlUtils.allActivities(WORKSPACE_SID)
        );
    }

    @Test
    public void testActivity() {
        assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Activities/AC123",
            UrlUtils.activity(WORKSPACE_SID, ACTIVITY_SID)
        );
    }

    @Test
    public void testWorkers() {
        assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Workers",
            UrlUtils.workers(WORKSPACE_SID)
        );
    }

    @Test
    public void testAllWorkers() {
        assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Workers/**",
            UrlUtils.allWorkers(WORKSPACE_SID)
        );
    }

    @Test
    public void testWorker() {
        assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Workers/WK123",
            UrlUtils.worker(WORKSPACE_SID, WORKER_SID)
        );
    }

    @Test
    public void testReservations() {
        assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Workers/WK123/Reservations",
            UrlUtils.reservations(WORKSPACE_SID, WORKER_SID)
        );
    }

    @Test
    public void testAllReservations() {
        assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Workers/WK123/Reservations/**",
            UrlUtils.allReservations(WORKSPACE_SID, WORKER_SID)
        );
    }

    @Test
    public void testReservation() {
        assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Workers/WK123/Reservations/WR123",
            UrlUtils.reservation(WORKSPACE_SID, WORKER_SID, RESERVATION_SID)
        );
    }

}
