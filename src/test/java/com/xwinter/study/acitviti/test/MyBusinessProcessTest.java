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
		ProcessInstance instance = activitiSpringRule.getRuntimeService()
				.startProcessInstanceByKey("TestProcess");
		assertNotNull(instance);
		Task task = activitiSpringRule.getTaskService().createTaskQuery()
				.processInstanceId(instance.getId()).singleResult();
		assertNotNull(task);
		assertEquals("部门领导审批", task.getName());
		activitiSpringRule.getTaskService().complete(task.getId());
		assertEquals(
				0,
				activitiSpringRule.getRuntimeService()
						.createProcessInstanceQuery()
						.processInstanceId(instance.getId()).count());
	}
}
