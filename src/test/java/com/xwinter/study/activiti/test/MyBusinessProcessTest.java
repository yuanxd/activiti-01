package com.xwinter.study.activiti.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;

public class MyBusinessProcessTest {
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();

	@Test
	@Deployment
	public void ruleUsageExample() {
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employeeName", "Kermit");
		variables.put("numberOfDays", new Integer(4));
		variables.put("vacationMotivation", "I'm really tired!");
		runtimeService.startProcessInstanceByKey("vacationRequest", variables);

		TaskService taskService = activitiRule.getTaskService();
		Task task = taskService.createTaskQuery().singleResult();

		assertEquals("Handle vacation request", task.getName());

		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskVariables.put("vacationApproved", "false");
		taskVariables.put("managerMotivation", "We have a tight deadline!");
		taskService.complete(task.getId(), taskVariables);
		assertEquals(1, runtimeService.createProcessInstanceQuery().count());
	}
}
