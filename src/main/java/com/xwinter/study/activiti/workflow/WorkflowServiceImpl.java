package com.xwinter.study.activiti.workflow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 工作流服务基类<br>
 * 注入工作流相关服务<br>
 * 注入用户服务userService
 * 
 * @author 袁晓冬
 * 
 */
@Service
public class WorkflowServiceImpl implements WorkflowService {
	/**
	 * 流程定义缓存
	 */
	protected static Map<String, ProcessDefinition> PROCESS_DEFINITION_CACHE = new HashMap<String, ProcessDefinition>();
	/** Service to manage Users and Groups. */
	@Autowired
	protected IdentityService identityService;

	/** Service which provides access to {@link Deployment}s */
	@Autowired
	protected RuntimeService runtimeService;

	/**
	 * Service which provides access to {@link Task} and form related
	 * operations.
	 */
	@Autowired
	protected TaskService taskService;

	/**
	 * Service providing access to the repository of process definitions and
	 * deployments.
	 */
	@Autowired
	protected RepositoryService repositoryService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String create(String businessKey, Map<String, Object> variables,
			String userid, String processDefinitionKey) {
		Assert.hasLength(processDefinitionKey);
		identityService.setAuthenticatedUserId(userid);
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey(processDefinitionKey, businessKey,
						variables);
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
		task.setDescription("yxd test");
		taskService.saveTask(task);
		return processInstance.getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void claim(String businessKey, String userid,
			String processDefinitionKey) {
		if (!StringUtils.hasLength(userid)) {
			throw new ActivitiException("can not get login user!");
		}
		// 流程定义key获取不到
		Assert.hasLength(processDefinitionKey);
		// 根据当前人未签收的任务
		Task task = taskService.createTaskQuery()
				.processDefinitionKey(processDefinitionKey)
				.taskCandidateUser(userid).active().orderByTaskPriority()
				.processInstanceBusinessKey(businessKey).desc()
				.orderByTaskCreateTime().desc().singleResult();
		if (null == task) {
			throw new ActivitiException("can not find task with businesskey:"
					+ businessKey);
		}
		taskService.claim(task.getId(), userid);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void complete(String businessKey, Map<String, Object> variables,
			String userid, String processDefinitionKey) {
		if (!StringUtils.hasLength(userid)) {
			throw new ActivitiException("can not get login user!");
		}
		// 流程定义key获取不到
		Assert.hasLength(processDefinitionKey);
		// 根据当前人签收的任务
		Task task = taskService.createTaskQuery()
				.processDefinitionKey(processDefinitionKey)
				.taskAssignee(userid).active().orderByTaskPriority()
				.processInstanceBusinessKey(businessKey).desc()
				.orderByTaskCreateTime().desc().singleResult();
		if (null == task) {
			throw new ActivitiException("can not find task with businesskey:"
					+ businessKey);
		}
		taskService.complete(task.getId(), variables);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Map<String, Object>> todoList(String userid) {
		// 结果集
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		// 时间格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

		// 已经签收的任务
		List<Task> todoList = taskService.createTaskQuery()
				.taskAssignee(userid).active().list();
		for (Task task : todoList) {
			String processDefinitionId = task.getProcessDefinitionId();
			ProcessDefinition processDefinition = getProcessDefinition(processDefinitionId);

			Map<String, Object> singleTask = packageTaskInfo(sdf, task,
					processDefinition);
			singleTask.put("status", "todo");
			result.add(singleTask);
		}
		// 等待签收的任务
		List<Task> toClaimList = taskService.createTaskQuery()
				.taskCandidateUser(userid).active().list();
		for (Task task : toClaimList) {
			String processDefinitionId = task.getProcessDefinitionId();
			ProcessDefinition processDefinition = getProcessDefinition(processDefinitionId);

			Map<String, Object> singleTask = packageTaskInfo(sdf, task,
					processDefinition);
			singleTask.put("status", "claim");
			result.add(singleTask);
		}

		return result;
	}

	/**
	 * 根据流程定义KEY查询流程定义
	 * 
	 * @param processDefinitionId
	 *            流程定义KEY
	 * @return ProcessDefinition
	 */
	private ProcessDefinition getProcessDefinition(String processDefinitionId) {
		ProcessDefinition processDefinition = PROCESS_DEFINITION_CACHE
				.get(processDefinitionId);
		if (processDefinition == null) {
			// 缓存中不存在时执行查询
			processDefinition = repositoryService
					.createProcessDefinitionQuery()
					.processDefinitionId(processDefinitionId).singleResult();
			PROCESS_DEFINITION_CACHE
					.put(processDefinitionId, processDefinition);
		}
		return processDefinition;
	}

	/**
	 * 待办中显示数据处理
	 * 
	 * @param sdf
	 *            SimpleDateFormat
	 * @param task
	 *            Task
	 * @param processDefinition
	 *            ProcessDefinition
	 * @return
	 */
	private Map<String, Object> packageTaskInfo(SimpleDateFormat sdf,
			Task task, ProcessDefinition processDefinition) {
		Map<String, Object> singleTask = new HashMap<String, Object>();
		singleTask.put("taskId", task.getId());
		singleTask.put("taskName", task.getName());
		singleTask.put("createTime", sdf.format(task.getCreateTime()));
		singleTask.put("pdname", processDefinition.getName());
		singleTask.put("pdversion", processDefinition.getVersion());
		singleTask.put("pid", task.getProcessInstanceId());
		return singleTask;
	}

}
