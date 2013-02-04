package com.xwinter.study.activiti.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xwinter.study.activiti.common.Utils;
import com.xwinter.study.activiti.entity.User;
import com.xwinter.study.activiti.workflow.WorkflowService;

/**
 * 流程管理控制器<br>
 * 处理不区分具体流程定义的功能。如待办列表、流程定义列表、流程发布等。<br>
 * 与具体流程相关的功能放{@link WorkflowService}基类中定义
 * 
 * @author 袁晓冬
 */
@Controller
@RequestMapping(value = "/workflow")
public class WorkflowController {

	@Autowired
	private WorkflowService workflowService;

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
		return workflowService.todoList(user.getId());
	}
}
