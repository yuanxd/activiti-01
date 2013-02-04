package com.xwinter.study.activiti.service.impl;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.xwinter.study.activiti.entity.BaseEntity;
import com.xwinter.study.activiti.entity.User;
import com.xwinter.study.activiti.service.WfBaseService;
import com.xwinter.study.activiti.workflow.WorkflowService;

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
	@Autowired
	protected WorkflowService workflowService;

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
		return workflowService.create(entity.getBusinessKey(), variables,
				currentUser.getId(), getProcessDefinitionKey());
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
		workflowService.claim(businessKey, currentUser.getId(),
				getProcessDefinitionKey());
	}

	/**
	 * 完成任务
	 * 
	 * @param businessKey
	 *            业务ID
	 */
	public void complete(E entity, Map<String, Object> variables,
			User currentUser) {
		inComplete(entity,variables,currentUser);
		workflowService.complete(entity.getBusinessKey(), variables, currentUser.getId(),
				getProcessDefinitionKey());
	}

	/**
	 * do nothing default<br>
	 */
	@Override
	public void inComplete(E entity, Map<String, Object> variables,
			User currentUser) {

	}
}
