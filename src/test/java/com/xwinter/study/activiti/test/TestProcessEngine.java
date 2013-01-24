package com.xwinter.study.activiti.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class TestProcessEngine {
	public static void main(String[] args) {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		repositoryService.createDeployment()
				.addClasspathResource("VacationRequest.bpmn20.xml").deploy();

		System.err.println("Number of process definitions: "
				+ repositoryService.createProcessDefinitionQuery().count());
		// Starting a process instance
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employeeName", "Kermit");
		variables.put("numberOfDays", new Integer(4));
		variables.put("vacationMotivation", "I'm really tired!");

		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("vacationRequest", variables);

		// Verify that we started a new process instance
		System.err.println("Number of process instances: "
				+ runtimeService.createProcessInstanceQuery().count());
		// Completing tasks
		// Fetch all tasks for the management group
		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery()
				.taskCandidateGroup("management").list();
		for (Task task : tasks) {
			System.err.println("Task available: " + task.getName());
		}
		Task task = tasks.get(0);

		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskVariables.put("vacationApproved", "false");
		taskVariables.put("managerMotivation", "We have a tight deadline!");
		taskService.complete(task.getId(), taskVariables);
		// Suspending and activating a process
//		repositoryService.suspendProcessDefinitionByKey("vacationRequest");
//		try {
//			runtimeService.startProcessInstanceByKey("vacationRequest");
//		} catch (ActivitiException e) {
//			repositoryService.activateProcessDefinitionByKey("vacationRequest");
//			e.printStackTrace();
//		}
		

	}
}
