package com.xwinter.study.activiti.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xwinter.study.activiti.dao.BaseDAO;
import com.xwinter.study.activiti.dao.TestDAO;
import com.xwinter.study.activiti.entity.Test;
import com.xwinter.study.activiti.service.TestService;

@Component
public class TestServiceImpl extends BaseServiceImpl<Test, String> implements
		TestService {
	@Autowired
	private TestDAO testDao;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	protected TaskService taskService;

	@Override
	protected BaseDAO<Test, String> getDAO() {
		return testDao;
	}

	@Override
	public String startWorkflow(Test test) {
		String pk = testDao.save(test);
		System.err.println("pk:" + pk);
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("TestProcess", pk);
		return processInstance.getId();
	}

	@Override
	public List<Test> getTodoList(String username) {

		List<Test> results = new ArrayList<Test>();
		List<Task> tasks = new ArrayList<Task>();

		// ���ݵ�ǰ�˵�ID��ѯ
		List<Task> todoList = taskService.createTaskQuery()
				.processDefinitionKey("TestProcess").taskAssignee(username)
				.active().orderByTaskPriority().desc().orderByTaskCreateTime()
				.desc().list();

		// ���ݵ�ǰ��δǩ�յ�����
		List<Task> unsignedTasks = taskService.createTaskQuery()
				.processDefinitionKey("TestProcess")
				.taskCandidateUser(username).active().orderByTaskPriority()
				.desc().orderByTaskCreateTime().desc().list();

		// �ϲ�
		tasks.addAll(todoList);
		tasks.addAll(unsignedTasks);

		// �������̵�ҵ��ID��ѯʵ�岢����
		for (Task task : tasks) {
			String processInstanceId = task.getProcessInstanceId();
			ProcessInstance processInstance = runtimeService
					.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).active()
					.singleResult();
			String businessKey = processInstance.getBusinessKey();
			Test test = testDao.get(businessKey);
			results.add(test);
		}
		return results;
	}
}
