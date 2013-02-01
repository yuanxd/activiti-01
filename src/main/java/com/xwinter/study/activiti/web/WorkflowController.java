package com.xwinter.study.activiti.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xwinter.study.activiti.common.Utils;
import com.xwinter.study.activiti.entity.User;

/**
 * 流程管理控制器<br>
 * 处理不区分具体流程定义的功能。如待办列表、流程定义列表、流程发布等。<br>
 * 与具体流程相关的功能放{@link WfBaseService}基类中定义
 * 
 * @author 袁晓冬
 */
@Controller
@RequestMapping(value = "/workflow")
public class WorkflowController {
	/**
	 * Service providing access to the repository of process definitions and
	 * deployments.
	 */
	@Autowired
	protected RepositoryService repositoryService;
	/**
	 * Service which provides access to {@link Task} and form related
	 * operations.
	 */
	@Autowired
	private TaskService taskService;
	/**
	 * 流程定义缓存
	 */
	protected static Map<String, ProcessDefinition> PROCESS_DEFINITION_CACHE = new HashMap<String, ProcessDefinition>();

	/**
	 * 待办任务查询
	 * 
	 * @param session
	 *            HttpSession
	 * @return List<Map<String, Object>> 以map方式存放待办列表数据
	 * @throws Exception
	 */
	@RequestMapping(value = "/task/todo/list")
	@ResponseBody
	public List<Map<String, Object>> todoList(HttpSession session) {
		// 从session中获取当前登录用户
		User user = Utils.getUserFromSession(session);
		// 结果集
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		// 时间格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

		// 已经签收的任务
		List<Task> todoList = taskService.createTaskQuery()
				.taskAssignee(user.getId()).active().list();
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
				.taskCandidateUser(user.getId()).active().list();
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
		singleTask.put("id", task.getId());
		singleTask.put("name", task.getName());
		singleTask.put("createTime", sdf.format(task.getCreateTime()));
		singleTask.put("pdname", processDefinition.getName());
		singleTask.put("pdversion", processDefinition.getVersion());
		singleTask.put("pid", task.getProcessInstanceId());
		return singleTask;
	}
}
