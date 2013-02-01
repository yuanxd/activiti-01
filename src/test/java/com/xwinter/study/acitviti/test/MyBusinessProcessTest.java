package com.xwinter.study.acitviti.test;

import java.util.List;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext-test.xml",
		"classpath:spring/applicationContext-workflow-test.xml" })
public class MyBusinessProcessTest {
	@Autowired
	@Rule
	public ActivitiRule activitiSpringRule;

	@Test
	@Deployment(resources = "diagrams/TestProcess.bpmn")
	public void simpleProcessTest() {
		String bussinessKey = "junit-" + System.currentTimeMillis();
		String processDefinitionKey = "TestProcess";
		/** 发起流程 */
		ProcessInstance instance = activitiSpringRule.getRuntimeService()
				.startProcessInstanceByKey(processDefinitionKey, bussinessKey);
		assertNotNull(instance);
		Task task = activitiSpringRule.getTaskService().createTaskQuery()
				.processInstanceBusinessKey(bussinessKey).singleResult();
		assertNotNull(task);
		assertEquals("部门领导审批", task.getName());
		/** 部门领导user2审批流程 */
		// 部门领导查询待办
		// 根据当前人未签收的任务
		List<Task> unsignedTasks = activitiSpringRule.getTaskService()
				.createTaskQuery().processDefinitionKey(processDefinitionKey)
				.taskCandidateUser("2")
				.processDefinitionKey(processDefinitionKey).active()
				.orderByTaskPriority().desc().orderByTaskCreateTime().desc()
				.list();
		assertTrue(unsignedTasks.size() > 0);
		// 签收
		for (Task temp : unsignedTasks) {
			activitiSpringRule.getTaskService().claim(temp.getId(), "2");
		}
		// 未签收任务为0
		unsignedTasks = activitiSpringRule.getTaskService().createTaskQuery()
				.processDefinitionKey(processDefinitionKey)
				.taskCandidateUser("2")
				.processInstanceBusinessKey(bussinessKey).active()
				.orderByTaskPriority().desc().orderByTaskCreateTime().desc()
				.list();
		assertTrue(unsignedTasks.size() == 0);
		// 已签收任务为1
		List<Task> todoList = activitiSpringRule.getTaskService()
				.createTaskQuery().processDefinitionKey(processDefinitionKey)
				.taskAssignee("2").processInstanceBusinessKey(bussinessKey)
				.active().orderByTaskPriority().desc().orderByTaskCreateTime()
				.desc().list();
		assertTrue(todoList.size() == 1);
		// 完成任务
		for (Task temp : todoList) {
			activitiSpringRule.getTaskService().complete(temp.getId());
		}
	}
}
