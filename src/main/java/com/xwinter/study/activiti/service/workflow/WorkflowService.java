package com.xwinter.study.activiti.service.workflow;

import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

import com.xwinter.study.activiti.web.form.workflow.TaskTodoForm;

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

	public List<TaskTodoForm> queryTodos(String userid);
}
