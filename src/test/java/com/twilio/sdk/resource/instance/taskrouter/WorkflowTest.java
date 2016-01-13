package com.twilio.sdk.resource.instance.taskrouter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.taskrouter.WorkflowConfiguration;
import com.twilio.sdk.taskrouter.WorkflowRule;
import com.twilio.sdk.taskrouter.WorkflowRuleTarget;

public class WorkflowTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/workflow.json");
	}

	@Test
	public void testCreateWorkflow() throws Exception {
		setExpectedServerReturnCode(201);
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("FriendlyName", "Test Workflow");
		String configuration = "{\"task_routing\":{\"filters\":[{\"targets\":[{\"queue\":\"WQec62de0e1148b8477f2e24579779c8b1\",\"expression\":\"task.language IN worker.languages\"}],\"friendly_name\":\"Sales\",\"expression\":\"type == \\\"sales\\\"\"},{\"targets\":[{\"queue\":\"WQ2acd4c1a41ffadce5d1bac9e1ce2fa9f\",\"expression\":\"task.language IN worker.languages\"}],\"friendly_name\":\"Marketing\",\"expression\":\"type == \\\"marketing\\\"\"},{\"targets\":[{\"queue\":\"WQe5eb317eb23500ade45087ea6522896c\",\"expression\":\"task.language IN worker.languages\"}],\"friendly_name\":\"Support\",\"expression\":\"type == \\\"support\\\"\"}],\"default_filter\":{\"queue\":\"WQ05f810d2d130344fd56e3c91ece2e594\"}}}";
		properties.put("Configuration", configuration);
		properties.put("AssignmentCallbackUrl", "http://example.com");
		Workflow workflow = taskRouterClient.createWorkflow("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", properties);
		assertNotNull(workflow);
		assertEquals("Default Fifo Workflow", workflow.getFriendlyName());
		assertEquals("{\"task_routing\":{\"filters\":[{\"targets\":[{\"queue\":\"WQec62de0e1148b8477f2e24579779c8b1\",\"expression\":\"task.language IN worker.languages\"}],\"friendly_name\":\"Sales\",\"expression\":\"type == \\\"sales\\\"\"},{\"targets\":[{\"queue\":\"WQ2acd4c1a41ffadce5d1bac9e1ce2fa9f\",\"expression\":\"task.language IN worker.languages\"}],\"friendly_name\":\"Marketing\",\"expression\":\"type == \\\"marketing\\\"\"},{\"targets\":[{\"queue\":\"WQe5eb317eb23500ade45087ea6522896c\",\"expression\":\"task.language IN worker.languages\"}],\"friendly_name\":\"Support\",\"expression\":\"type == \\\"support\\\"\"}],\"default_filter\":{\"queue\":\"WQ05f810d2d130344fd56e3c91ece2e594\"}}}", workflow.getConfiguration());
		assertEquals("http://example.com", workflow.getAssignmentCallbackUrl());
	}
	
	@Test
	public void testCreateWorkflowWithHelper() throws Exception {
		setExpectedServerReturnCode(201);
		
		// construct workflow
		List<WorkflowRule> rules = new ArrayList<WorkflowRule>();
		
		// sales
		List<WorkflowRuleTarget> salesTargets = new ArrayList<WorkflowRuleTarget>();
		WorkflowRuleTarget salesTarget = new WorkflowRuleTarget("WQec62de0e1148b8477f2e24579779c8b1", "task.language IN worker.languages", null, null);
		salesTargets.add(salesTarget);
		WorkflowRule salesRule = new WorkflowRule("type == \"sales\"", salesTargets);
		
		// marketing
		List<WorkflowRuleTarget> marketingTargets = new ArrayList<WorkflowRuleTarget>();
		WorkflowRuleTarget marketingTarget = new WorkflowRuleTarget("WQ2acd4c1a41ffadce5d1bac9e1ce2fa9f", "task.language IN worker.languages", null, null);
		marketingTargets.add(marketingTarget);
		WorkflowRule marketingRule = new WorkflowRule("type == \"marketing\"", marketingTargets);
		
		// support
		List<WorkflowRuleTarget> supportTargets = new ArrayList<WorkflowRuleTarget>();
		WorkflowRuleTarget supportTarget = new WorkflowRuleTarget("WQe5eb317eb23500ade45087ea6522896c", "task.language IN worker.languages", null, null);
		supportTargets.add(supportTarget);
		WorkflowRule supportRule = new WorkflowRule("type == \"support\"", marketingTargets);
		
		// default filter
		WorkflowRuleTarget defaultTarget = new WorkflowRuleTarget("WQ05f810d2d130344fd56e3c91ece2e594");

		// put rules together in order in which the workflow should process them
		rules.add(salesRule);
		rules.add(marketingRule);
		rules.add(supportRule);
		
		// build workflow & convert to json
		WorkflowConfiguration config = new WorkflowConfiguration(rules, defaultTarget);
		String workflowJSON = config.toJSON();
		
		// build up properties for workflow
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("FriendlyName", "Test Workflow");
		properties.put("Configuration", workflowJSON);
		properties.put("Timeout", "120");
		
		String configurationStr = "{\"task_routing\":{\"filters\":[{\"targets\":[{\"queue\":\"WQec62de0e1148b8477f2e24579779c8b1\",\"expression\":\"task.language IN worker.languages\"}],\"friendly_name\":\"Sales\",\"expression\":\"type == \\\"sales\\\"\"},{\"targets\":[{\"queue\":\"WQ2acd4c1a41ffadce5d1bac9e1ce2fa9f\",\"expression\":\"task.language IN worker.languages\"}],\"friendly_name\":\"Marketing\",\"expression\":\"type == \\\"marketing\\\"\"},{\"targets\":[{\"queue\":\"WQe5eb317eb23500ade45087ea6522896c\",\"expression\":\"task.language IN worker.languages\"}],\"friendly_name\":\"Support\",\"expression\":\"type == \\\"support\\\"\"}],\"default_filter\":{\"queue\":\"WQ05f810d2d130344fd56e3c91ece2e594\"}}}";

		Workflow workflow = taskRouterClient.createWorkflow("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", properties);
		assertNotNull(workflow);
		
		assertEquals("Default Fifo Workflow", workflow.getFriendlyName());
		assertEquals(configurationStr, workflow.getConfiguration());
		assertEquals("http://example.com", workflow.getAssignmentCallbackUrl());
		assertNull(workflow.getFallbackAssignmentCallbackUrl());
		assertEquals(120, workflow.getTaskReservationTimeout().intValue());
	}
	
	@Test
		public void testSerializeWorkflowFriendlyName() throws Exception {
		setExpectedServerReturnCode(201);
		// marshal back and check for friendly_name
		Map<String, String> properties2 = new HashMap<String, String>();
		properties2.put("FriendlyName", "Test Workflow Filter Friendly Name");
		String configurationStrWithFriendlyName = "{\"task_routing\":{\"filters\":[{\"expression\":\"type == \\\"sales\\\"\",\"targets\":[{\"queue\":\"WQec62de0e1148b8477f2e24579779c8b1\",\"expression\":\"task.language IN worker.languages\"}],\"friendly_name\":\"Sales\"},{\"expression\":\"type == \\\"marketing\\\"\",\"targets\":[{\"queue\":\"WQ2acd4c1a41ffadce5d1bac9e1ce2fa9f\",\"expression\":\"task.language IN worker.languages\"}],\"friendly_name\":\"Marketing\"},{\"expression\":\"type == \\\"support\\\"\",\"targets\":[{\"queue\":\"WQe5eb317eb23500ade45087ea6522896c\",\"expression\":\"task.language IN worker.languages\"}],\"friendly_name\":\"Support\"}],\"default_filter\":{\"queue\":\"WQ05f810d2d130344fd56e3c91ece2e594\"}}}";
		properties2.put("Configuration", configurationStrWithFriendlyName);
		properties2.put("AssignmentCallbackUrl", "http://exampleo.com");

		Workflow workflow2 = taskRouterClient.createWorkflow("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", properties2);
		WorkflowConfiguration workflowConfiguration2 = workflow2.parseConfiguration();

		assertEquals(workflowConfiguration2.toJSON(), configurationStrWithFriendlyName);
	}
	
	@Test
	public void testWorkflowWithFilterFriendlyName() throws Exception {
		setExpectedServerReturnCode(201);
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/workflow_filter_friendly_name.json");

		Map<String, String> properties = new HashMap<String, String>();
		properties.put("FriendlyName", "Test Workflow Filter Friendly Name");
		String configurationStr = "{\"task_routing\":{\"filters\":[{\"targets\":[{\"queue\":\"WQec62de0e1148b8477f2e24579779c8b1\",\"expression\":\"task.language IN worker.languages\"}],\"filter_friendly_name\":\"Sales\",\"expression\":\"type == \\\"sales\\\"\"},{\"targets\":[{\"queue\":\"WQ2acd4c1a41ffadce5d1bac9e1ce2fa9f\",\"expression\":\"task.language IN worker.languages\"}],\"filter_friendly_name\":\"Marketing\",\"expression\":\"type == \\\"marketing\\\"\"},{\"targets\":[{\"queue\":\"WQe5eb317eb23500ade45087ea6522896c\",\"expression\":\"task.language IN worker.languages\"}],\"filter_friendly_name\":\"Support\",\"expression\":\"type == \\\"support\\\"\"}],\"default_filter\":{\"queue\":\"WQ05f810d2d130344fd56e3c91ece2e594\"}}}";
		properties.put("Configuration", configurationStr);
		properties.put("AssignmentCallbackUrl", "http://example.com");

		Workflow workflow = taskRouterClient.createWorkflow("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", properties);
		WorkflowConfiguration workflowConfiguration = workflow.parseConfiguration();
		List<WorkflowRule> workflowRules = workflowConfiguration.getWorkflowRules();

		assertEquals(workflowRules.get(0).getFriendlyName(), "Sales");
		assertEquals(workflowRules.get(0).getFilterFriendlyName(), "Sales");
		assertEquals(workflowRules.get(1).getFriendlyName(), "Marketing");
		assertEquals(workflowRules.get(1).getFilterFriendlyName(), "Marketing");
		assertEquals(workflowRules.get(2).getFriendlyName(), "Support");
		assertEquals(workflowRules.get(2).getFilterFriendlyName(), "Support");
	}

	@Test
	public void testDeleteWorkflow() throws Exception {
		setExpectedServerAnswer(null);
		setExpectedServerReturnCode(204);
		assertTrue(taskRouterClient.deleteWorkflow("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WFaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}

	@Test
	public void testGetWorkflow() throws Exception {
		setExpectedServerReturnCode(200);
		Workflow workflow = taskRouterClient.getWorkflow("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WFaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(workflow);
		assertEquals("WFaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", workflow.getSid());
		assertEquals("Default Fifo Workflow", workflow.getFriendlyName());
	}
}
