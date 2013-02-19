package com.xwinter.study.activiti.workflow;

import java.util.List;
import java.util.Map;

/**
 * 工作流服务基类接口<br>
 * 需要使用工作流的服务实现此接口，并集成WfBaseServiceImpl类
 * 
 * @author 袁晓冬
 * 
 */
public interface WorkflowService {

	/**
	 * 提交记录，发起工作流<br>
	 * 根据业务主键、流程变量列表、发起人、流程定义发起流程
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
	 * @return String The unique identifier of the execution.
	 */
	public String create(String businessKey, Map<String, Object> variables,
			String userid, String processDefinitionKey);

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
