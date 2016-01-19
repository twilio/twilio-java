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
	public void testWorkflowWithFriendlyName() throws Exception {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("FriendlyName", "Test Workflow Filter Friendly Name");
		// serialize with "friendly_name" in the configuration
		String configurationStrWithFriendlyName = "{\"task_routing\":{\"filters\":[{\"expression\":\"type == \\\"sales\\\"\",\"targets\":[{\"queue\":\"WQec62de0e1148b8477f2e24579779c8b1\",\"expression\":\"task.language IN worker.languages\"}],\"friendly_name\":\"Sales\"},{\"expression\":\"type == \\\"marketing\\\"\",\"targets\":[{\"queue\":\"WQ2acd4c1a41ffadce5d1bac9e1ce2fa9f\",\"expression\":\"task.language IN worker.languages\"}],\"friendly_name\":\"Marketing\"},{\"expression\":\"type == \\\"support\\\"\",\"targets\":[{\"queue\":\"WQe5eb317eb23500ade45087ea6522896c\",\"expression\":\"task.language IN worker.languages\"}],\"friendly_name\":\"Support\"}],\"default_filter\":{\"queue\":\"WQ05f810d2d130344fd56e3c91ece2e594\"}}}";
		properties.put("Configuration", configurationStrWithFriendlyName);
		properties.put("AssignmentCallbackUrl", "http://exampleo.com");

		Workflow workflow = taskRouterClient.createWorkflow("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", properties);
		WorkflowConfiguration workflowConfiguration = workflow.parseConfiguration();

		// check that serializing with "friendly_name" in the configuration marshaled as "friendly_name"
		assertEquals(workflowConfiguration.toJSON(), configurationStrWithFriendlyName);
	}
	
	@Test
	public void testWorkflowWithFilterFriendlyName() throws Exception {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("FriendlyName", "Test Workflow Filter Friendly Name");
		// serialize with "filter_friendly_name" in the configuration
		String configurationStrWithFilterFriendlyName = "{\"task_routing\":{\"filters\":[{\"targets\":[{\"queue\":\"WQec62de0e1148b8477f2e24579779c8b1\",\"expression\":\"task.language IN worker.languages\"}],\"filter_friendly_name\":\"Sales\",\"expression\":\"type == \\\"sales\\\"\"},{\"targets\":[{\"queue\":\"WQ2acd4c1a41ffadce5d1bac9e1ce2fa9f\",\"expression\":\"task.language IN worker.languages\"}],\"filter_friendly_name\":\"Marketing\",\"expression\":\"type == \\\"marketing\\\"\"},{\"targets\":[{\"queue\":\"WQe5eb317eb23500ade45087ea6522896c\",\"expression\":\"task.language IN worker.languages\"}],\"filter_friendly_name\":\"Support\",\"expression\":\"type == \\\"support\\\"\"}],\"default_filter\":{\"queue\":\"WQ05f810d2d130344fd56e3c91ece2e594\"}}}";
		properties.put("Configuration", configurationStrWithFilterFriendlyName);
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
		
		// confirm expected JSON to contain "friendly_name" and not "filter_friendly_name"
		String expectedConfigurationStr = "{\"task_routing\":{\"filters\":[{\"expression\":\"type == \\\"sales\\\"\",\"targets\":[{\"queue\":\"WQec62de0e1148b8477f2e24579779c8b1\",\"expression\":\"task.language IN worker.languages\"}],\"friendly_name\":\"Sales\"},{\"expression\":\"type == \\\"marketing\\\"\",\"targets\":[{\"queue\":\"WQ2acd4c1a41ffadce5d1bac9e1ce2fa9f\",\"expression\":\"task.language IN worker.languages\"}],\"friendly_name\":\"Marketing\"},{\"expression\":\"type == \\\"support\\\"\",\"targets\":[{\"queue\":\"WQe5eb317eb23500ade45087ea6522896c\",\"expression\":\"task.language IN worker.languages\"}],\"friendly_name\":\"Support\"}],\"default_filter\":{\"queue\":\"WQ05f810d2d130344fd56e3c91ece2e594\"}}}";
		assertEquals(workflowConfiguration.toJSON(), expectedConfigurationStr);
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
