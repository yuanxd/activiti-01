package com.xwinter.study.activiti.service.impl;

import java.io.Serializable;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.xwinter.study.activiti.common.AppException;
import com.xwinter.study.activiti.entity.BaseEntity;
import com.xwinter.study.activiti.entity.User;
import com.xwinter.study.activiti.service.UserService;
import com.xwinter.study.activiti.service.WfBaseService;

/**
 * 工作流服务基类<br>
 * 注入工作流相关服务<br>
 * 注入用户服务userService
 * 
 * @author 袁晓冬
 * 
 */
public abstract class WfBaseServiceImpl<E extends BaseEntity, PK extends Serializable>
		extends BaseServiceImpl<E, PK> implements WfBaseService<E, PK> {
	/** Service to manage {@link User}s and {@link Group}s. */
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

	/** 用户服务 */
	@Autowired
	protected UserService userService;

	/**
	 * 提交记录，发起工作流
	 * 
	 * @param businessKey
	 *            a key that uniquely identifies the process instance in the
	 *            context or the given process definition.
	 * @param variables
	 *            the variables to pass, can be null.
	 * @return processInstanceId
	 */
	public String create(E entity, Map<String, Object> variables,
			User currentUser) {
		String key = getProcessDefinitionKey();
		Assert.hasLength(key);
		inCreate(entity, variables, currentUser);
		identityService.setAuthenticatedUserId(currentUser.getName());
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey(key, entity.getBusinessKey(),
						variables);
		return processInstance.getId();
	}

	/**
	 * do nothing default<br>
	 * {@inheritDoc}
	 */
	@Override
	public void inCreate(E entity, Map<String, Object> variables,
			User currentUser) {

	}

	/**
	 * 工作流-签收 {@inheritDoc}
	 */
	public void claim(String businessKey, User currentUser) {
		// 当前登录用户名
		String currUserName = currentUser.getName();
		if (!StringUtils.hasLength(currUserName)) {
			throw new AppException("can not get login user!",
					AppException.INFTYPE_ERROR);
		}
		// 流程定义key
		String processKey = getProcessDefinitionKey();
		// 流程定义key获取不到
		Assert.hasLength(processKey);
		// 根据当前人未签收的任务
		Task task = taskService.createTaskQuery()
				.processDefinitionKey(processKey)
				.taskCandidateUser(currUserName).active().orderByTaskPriority()
				.processInstanceBusinessKey(businessKey).desc()
				.orderByTaskCreateTime().desc().singleResult();
		if (null == task) {
			throw new AppException("can not find task with businesskey:"
					+ businessKey);
		}
		taskService.claim(task.getId(), currUserName);
	}

	/**
	 * 完成任务
	 * 
	 * @param businessKey
	 *            业务ID
	 */
	public void complete(String businessKey, Map<String, Object> variables,
			User currentUser) {
		// 当前登录用户名
		String currUserName = currentUser.getName();
		if (!StringUtils.hasLength(currUserName)) {
			throw new AppException("can not get login user!",
					AppException.INFTYPE_ERROR);
		}
		// 流程定义key
		String processKey = getProcessDefinitionKey();
		// 流程定义key获取不到
		Assert.hasLength(processKey);
		inComplete(businessKey, variables, currentUser);
		// 根据当前人签收的任务
		Task task = taskService.createTaskQuery()
				.processDefinitionKey(processKey).taskAssignee(currUserName)
				.active().orderByTaskPriority()
				.processInstanceBusinessKey(businessKey).desc()
				.orderByTaskCreateTime().desc().singleResult();
		if (null == task) {
			throw new AppException("can not find task with businesskey:"
					+ businessKey);
		}
		taskService.complete(task.getId(), variables);
	}

	/**
	 * do nothing default<br>
	 */
	@Override
	public void inComplete(String businessKey, Map<String, Object> variables,
			User currentUser) {

	}

	/**
	 * 查询流程定义对象
	 * 
	 * @param processDefinitionId
	 *            流程定义ID
	 * @return
	 */
	protected ProcessDefinition getProcessDefinition(String processDefinitionId) {
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		return processDefinition;
	}

}
