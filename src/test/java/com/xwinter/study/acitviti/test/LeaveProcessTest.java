package com.xwinter.study.acitviti.test;

import java.util.List;

import org.activiti.engine.test.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xwinter.study.activiti.common.Assert;
import com.xwinter.study.activiti.common.ProcessJunitHelper;
import com.xwinter.study.activiti.entity.Leave;
import com.xwinter.study.activiti.service.leave.LeaveService;
import com.xwinter.study.activiti.service.workflow.WorkflowService;
import com.xwinter.study.activiti.web.form.workflow.TaskTodoForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:springtest/applicationContext*.xml" })
public class LeaveProcessTest {
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private ProcessJunitHelper helper;

	@Deployment
	@Test
	public void testCreate() {
		// 启动流程
		Leave leave = new Leave();
		leave.setName("yxd test leave");
		leaveService.create(leave, null, "yxd");
		String businessKey = leave.getId();
		// 待办验证
		TaskTodoForm form = helper.getTaskByUserAndBusinessKey("leader",
				businessKey);
		Assert.assertNotNull(form);
		Assert.assertTaskNotClaimed(form);
		
	}

	@Test
	@Deployment
	public void testQueryTodos() {
		List<TaskTodoForm> formList = workflowService.queryTodos("leader");
		System.err.println(formList.size());
	}

}
