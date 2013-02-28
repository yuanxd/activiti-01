package com.xwinter.study.acitviti.test;

import org.activiti.engine.test.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xwinter.study.activiti.service.wf.WorkflowService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:springtest/applicationContext*.xml" })
public class ProcessTest {
	/** 工作流服务 */
	@Autowired
	protected WorkflowService workflowService;

	@Test
	@Deployment
	public void simpleProcessTest() {
		workflowService.startProcess("leaveProcess", "test", null, "yxd");
	}
}
