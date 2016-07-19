package com.twilio.jwt.taskrouter;

import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces",
            UrlUtils.workspaces()
        );
    }

    @Test
    public void testAllWorkspaces() {
        Assert.assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/**",
            UrlUtils.allWorkspaces()
        );
    }

    @Test
    public void testWorkspace() {
        Assert.assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123",
            UrlUtils.workspace(WORKSPACE_SID)
        );
    }

    @Test
    public void testTaskQueues() {
        Assert.assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/TaskQueues",
            UrlUtils.taskQueues(WORKSPACE_SID)
        );
    }

    @Test
    public void testAllTaskQueues() {
        Assert.assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/TaskQueues/**",
            UrlUtils.allTaskQueues(WORKSPACE_SID)
        );
    }

    @Test
    public void testTaskQueue() {
        Assert.assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/TaskQueues/TQ123",
            UrlUtils.taskQueue(WORKSPACE_SID, TASK_QUEUE_SID)
        );
    }

    @Test
    public void testTasks() {
        Assert.assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Tasks",
            UrlUtils.tasks(WORKSPACE_SID)
        );
    }

    @Test
    public void testAllTasks() {
        Assert.assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Tasks/**",
            UrlUtils.allTasks(WORKSPACE_SID)
        );
    }

    @Test
    public void testTask() {
        Assert.assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Tasks/TK123",
            UrlUtils.task(WORKSPACE_SID, TASK_SID)
        );
    }

    @Test
    public void testActivities() {
        Assert.assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Activities",
            UrlUtils.activities(WORKSPACE_SID)
        );
    }

    @Test
    public void testAllActivities() {
        Assert.assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Activities/**",
            UrlUtils.allActivities(WORKSPACE_SID)
        );
    }

    @Test
    public void testActivity() {
        Assert.assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Activities/AC123",
            UrlUtils.activity(WORKSPACE_SID, ACTIVITY_SID)
        );
    }

    @Test
    public void testWorkers() {
        Assert.assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Workers",
            UrlUtils.workers(WORKSPACE_SID)
        );
    }

    @Test
    public void testAllWorkers() {
        Assert.assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Workers/**",
            UrlUtils.allWorkers(WORKSPACE_SID)
        );
    }

    @Test
    public void testWorker() {
        Assert.assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Workers/WK123",
            UrlUtils.worker(WORKSPACE_SID, WORKER_SID)
        );
    }

    @Test
    public void testReservations() {
        Assert.assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Workers/WK123/Reservations",
            UrlUtils.reservations(WORKSPACE_SID, WORKER_SID)
        );
    }

    @Test
    public void testAllReservations() {
        Assert.assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Workers/WK123/Reservations/**",
            UrlUtils.allReservations(WORKSPACE_SID, WORKER_SID)
        );
    }

    @Test
    public void testReservation() {
        Assert.assertEquals(
            "https://taskrouter.twilio.com/v1/Workspaces/WS123/Workers/WK123/Reservations/WR123",
            UrlUtils.reservation(WORKSPACE_SID, WORKER_SID, RESERVATION_SID)
        );
    }

}
