package com.xwinter.study.activiti.service;

import java.io.Serializable;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

import com.xwinter.study.activiti.entity.BaseWorkflowEntity;
import com.xwinter.study.activiti.entity.identity.User;

/**
 * 工作流服务基类接口<br>
 * 需要使用工作流的服务实现此接口，并集成WfBaseServiceImpl类
 * 
 * @author 袁晓冬
 * 
 */
public interface BaseWorkflowService<E extends BaseWorkflowEntity, PK extends Serializable> {
	/**
	 * 提交记录，发起工作流<br>
	 * 根据业务主键、流程变量列表，发起人发起流程
	 * 
	 * @param businessKey
	 *            a key that uniquely identifies the process instance in the
	 *            context or the given process definition.
	 * @param variables
	 *            the variables to pass, can be null.
	 * @return processInstanceId
	 */
	public ProcessInstance create(E entity, Map<String, Object> variables,
			User currentUser);

	/**
	 * processDefinitionKey key of process definition, cannot be null.
	 * 
	 * @return processDefinitionKey
	 */
	public String getProcessDefinitionKey();

}
