package com.xwinter.study.activiti.service.wf;

import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

/**
 * 工作流服务基类接口<br>
 * 需要使用工作流的服务实现此接口，并集成WfBaseServiceImpl类
 * 
 * @author 袁晓冬
 * 
 */
public interface WorkflowService {

	/**
	 * *发起流程<br>
	 * 
	 * @param processDefinitionKey
	 *            工作流key
	 * @param businessKey
	 *            业务ID
	 * @param variables
	 *            流程变量
	 * @param userid
	 *            流程发起人
	 * @return ProcessInstance
	 */
	public ProcessInstance startProcess(String processDefinitionKey,
			String businessKey, Map<String, Object> variables, String userid);

	/**
	 * 签收工作流
	 * 
	 * @param businessKey
	 *            a key that uniquely identifies the process instance in the
	 *            context or the given process definition.
	 * @param userid
	 *            authenticated user id.
	 * @param processDefinitionKey
	 *            key of process definition, cannot be null.
	 */
	public void claim(String businessKey, String userid,
			String processDefinitionKey);

	/**
	 * 完成任务，进入流程下一步
	 * 
	 * @param businessKey
	 *            a key that uniquely identifies the process instance in the
	 *            context or the given process definition.
	 * @param variables
	 *            the variables to pass, can be null.
	 * @param userid
	 *            authenticated user id.
	 * @param processDefinitionKey
	 *            key of process definition, cannot be null.
	 */
	public void complete(String businessKey, Map<String, Object> variables,
			String userid, String processDefinitionKey);

	/**
	 * 待办任务查询
	 * 
	 * @param userid
	 *            authenticated user id.
	 * @return List<Map<String, Object>> 以map方式存放待办列表数据
	 */
	public List<Map<String, Object>> todoList(String userid);

}
