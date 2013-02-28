package com.xwinter.study.activiti.web.workflow;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xwinter.study.activiti.common.Utils;
import com.xwinter.study.activiti.entity.identity.User;
import com.xwinter.study.activiti.service.workflow.WorkflowService;

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
}