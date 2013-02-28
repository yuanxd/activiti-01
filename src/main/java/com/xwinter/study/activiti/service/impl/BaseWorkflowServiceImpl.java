package com.xwinter.study.activiti.service.impl;

import java.io.Serializable;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.xwinter.study.activiti.dao.BaseDAO;
import com.xwinter.study.activiti.entity.BaseWorkflowEntity;
import com.xwinter.study.activiti.service.BaseWorkflowService;
import com.xwinter.study.activiti.service.workflow.WorkflowService;

/**
 * 工作流服务基类<br>
 * 注入工作流相关服务<br>
 * 注入用户服务userService
 * 
 * @author 袁晓冬
 * 
 */
public abstract class BaseWorkflowServiceImpl<E extends BaseWorkflowEntity, PK extends Serializable>
		extends BaseServiceImpl<E, PK> implements BaseWorkflowService<E, PK> {
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
	public ProcessInstance create(E entity, Map<String, Object> variables,
			String user) {
		String key = getProcessDefinitionKey();
		Assert.hasLength(key);
		return workflowService.startProcess(getProcessDefinitionKey(),
				entity.getBusinessKey(), variables, user);
	}
}
