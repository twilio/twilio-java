package com.twilio.sdk.examples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.instance.taskrouter.Activity;
import com.twilio.sdk.resource.instance.taskrouter.Task;
import com.twilio.sdk.resource.instance.taskrouter.TaskQueue;
import com.twilio.sdk.resource.instance.taskrouter.Worker;
import com.twilio.sdk.resource.instance.taskrouter.Workflow;
import com.twilio.sdk.resource.instance.taskrouter.Workspace;
import com.twilio.sdk.resource.instance.taskrouter.WorkspaceStatistics;
import com.twilio.sdk.resource.list.taskrouter.ActivityList;
import com.twilio.sdk.resource.list.taskrouter.TaskList;
import com.twilio.sdk.resource.list.taskrouter.TaskQueueList;
import com.twilio.sdk.resource.list.taskrouter.WorkerList;
import com.twilio.sdk.resource.list.taskrouter.WorkflowList;
import com.twilio.sdk.taskrouter.WorkflowConfiguration;
import com.twilio.sdk.taskrouter.WorkflowRule;
import com.twilio.sdk.taskrouter.WorkflowRuleTarget;

public class TaskRouterExamples {

	private static final String ACCOUNT_SID = "YourAccountSid";
	private static final String AUTH_TOKEN = "YourAuthToken";
	private static final String WORKSPACE_SID = "YourWorkspaceSid";

	public static void main(String[] args) throws Exception {

		TwilioTaskRouterClient trClient = new TwilioTaskRouterClient(ACCOUNT_SID, AUTH_TOKEN);
		Workspace workspace = trClient.getWorkspace(WORKSPACE_SID);

		ActivityList activities = workspace.getActivities();
		WorkflowList workflows = workspace.getWorkflows();
		TaskQueueList taskQueues = workspace.getTaskQueues();
		WorkerList workers = workspace.getWorkers();
		TaskList tasks = workspace.getTasks();

		for(Activity activity : activities) {
			System.out.println("Activity: "+activity.getFriendlyName());
		}
		Workflow firstWorkflow = workflows.iterator().next();
		for(Workflow workflow : workflows) {
			System.out.println("Workflow: "+workflow.getFriendlyName());
		}
		for(TaskQueue taskQueue : taskQueues) {
			System.out.println("TaskQueue: "+taskQueue.getFriendlyName());
		}
		for(Worker worker : workers) {
			System.out.println("Worker: "+worker.getFriendlyName());
		}
		for(Task task : tasks) {
			System.out.println("Task: "+task.parseAttributes());
			trClient.deleteTask(WORKSPACE_SID, task.getSid());
		}

		Map<String, String> taskAttributes = new HashMap<String, String>();
		taskAttributes.put("foo", "bar");

		Task createdTask = workspace.createTask(firstWorkflow.getSid(), taskAttributes, null, null);
		System.out.println("created a task: "+createdTask.parseAttributes());
		Task readTask = workspace.getTask(createdTask.getSid());
		System.out.println("foo attributes: "+readTask.parseAttributes().get("foo"));

		WorkspaceStatistics statistics = workspace.getStatistics();
		System.out.println("---- statistics ----");
		System.out.println("StartTime: "+statistics.getStartTime().getTime());
		System.out.println("EndTime: "+statistics.getEndTime().getTime());
		System.out.println("Avg Task Acceptance Time: "+statistics.getAverageTaskAcceptanceTime());
		System.out.println("Tasks Created: "+statistics.getTasksCreated());

		Workflow workflow = buildWorkflow(workspace);

		readWorkflow(trClient, workspace.getSid(), workflow.getSid());
	}

	private static Workflow buildWorkflow(Workspace workspace) throws Exception {
		// construct workflow
		List<WorkflowRule> rules = new ArrayList<WorkflowRule>();

		String salesQueueSid = "YourSalesQueueSid";
		String marketingQueueSid = "YourMarketingQueueSid";
		String supportQueueSid = "YourSupportQueueSid";
		String defaultQueue = "YourDefaultQueueSid";

		// sales
		List<WorkflowRuleTarget> salesTargets = new ArrayList<WorkflowRuleTarget>();
		WorkflowRuleTarget salesTarget = new WorkflowRuleTarget(salesQueueSid, null, null, null);
		salesTargets.add(salesTarget);
		WorkflowRule salesRule = new WorkflowRule("type == \"sales\"", salesTargets);

		// marketing
		List<WorkflowRuleTarget> marketingTargets = new ArrayList<WorkflowRuleTarget>();
		WorkflowRuleTarget marketingTarget = new WorkflowRuleTarget(marketingQueueSid, null, null, null);
		marketingTargets.add(marketingTarget);
		WorkflowRule marketingRule = new WorkflowRule("type == \"marketing\"", marketingTargets);

		// support
		List<WorkflowRuleTarget> supportTargets = new ArrayList<WorkflowRuleTarget>();
		WorkflowRuleTarget supportTarget = new WorkflowRuleTarget(supportQueueSid, null, null, null);
		supportTargets.add(supportTarget);
		WorkflowRule supportRule = new WorkflowRule("type == \"support\"", supportTargets);

		// default filter
		WorkflowRuleTarget defaultTarget = new WorkflowRuleTarget(defaultQueue);

		// put rules together in order in which the workflow should process them
		rules.add(salesRule);
		rules.add(marketingRule);
		rules.add(supportRule);

		// build workflow & convert to json
		WorkflowConfiguration config = new WorkflowConfiguration(rules, defaultTarget);
		String workflowJSON = config.toJSON();

		Map<String, String> params = new HashMap<String, String>();
		params.put("Configuration", workflowJSON);
		params.put("FriendlyName", "New SalesMarketingSupport Workflow");
		params.put("AssignmentCallbackUrl", "http://example.com");
		Workflow workflow = workspace.createWorkflow(params);
		return workflow;
	}

	private static void readWorkflow(TwilioTaskRouterClient client, String workspaceSid, String workflowSid) throws IOException {
		Workflow workflow = client.getWorkflow(workspaceSid, workflowSid);

		// show that we can inspect the workflow configuration
		WorkflowConfiguration config = workflow.parseConfiguration();
		System.out.println(config);

		List<WorkflowRule> workflowRules = config.getWorkflowRules();
		WorkflowRuleTarget defaultTarget = config.getDefaultTarget();
	}
}
