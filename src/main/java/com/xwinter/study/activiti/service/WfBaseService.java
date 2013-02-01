package com.xwinter.study.activiti.service;

import java.io.Serializable;
import java.util.Map;

import com.xwinter.study.activiti.entity.BaseEntity;
import com.xwinter.study.activiti.entity.User;

/**
 * 工作流服务基类接口<br>
 * 需要使用工作流的服务实现此接口，并集成WfBaseServiceImpl类
 * 
 * @author 袁晓冬
 * 
 */
public interface WfBaseService<E extends BaseEntity, PK extends Serializable>
		extends BaseService<E, PK> {
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
	public String create(E entity, Map<String, Object> variables,
			User currentUser);

	/**
	 * 当调用发起工作流时在此方法内执行业务处理<br>
	 * 如业务数据状态更新.
	 * 
	 * @param businessKey
	 *            业务数据key
	 * @param variables
	 *            流程变量
	 */
	public void inCreate(E entity, Map<String, Object> variables,
			User currentUser);

	/**
	 * 签收工作流
	 * 
	 * @param businessKey
	 *            业务记录ID
	 */
	public void claim(String businessKey, User currentUser);

	/**
	 * 完成任务
	 * 
	 * @param businessKey
	 *            业务ID
	 * @return
	 */
	public void complete(String businessKey, Map<String, Object> variables,
			User currentUser);

	/**
	 * 完成任务
	 * 
	 * @param businessKey
	 *            业务ID
	 * @return
	 */
	public void inComplete(String businessKey, Map<String, Object> variables,
			User currentUser);

	/**
	 * 根据业务key获取业务对象
	 * 
	 * @param businessKey
	 *            业务key
	 * @return ? extends BaseEntity
	 */
	public BaseEntity getEntityByBusinessKey(String businessKey);

	/**
	 * processDefinitionKey key of process definition, cannot be null.
	 * 
	 * @return processDefinitionKey
	 */
	public String getProcessDefinitionKey();

}
