package com.xwinter.study.activiti.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xwinter.study.activiti.common.Constants;
import com.xwinter.study.activiti.entity.Role;
import com.xwinter.study.activiti.entity.Test;
import com.xwinter.study.activiti.entity.User;
import com.xwinter.study.activiti.service.MainService;
import com.xwinter.study.activiti.service.RoleService;

/**
 * 首页Controller
 * 
 * @author 袁晓冬
 * 
 */
@Controller
@RequestMapping(value = "/index")
public class MainController {
	// @Autowired
	// private MainService testService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MainService mainService;

	@RequestMapping(value = "start", method = RequestMethod.POST)
	public String start(Test test, RedirectAttributes redirectAttributes,
			HttpSession session) {
		Map<String, Object> variables = new HashMap<String, Object>();
		Role role = roleService.getByName("leader");
		variables.put("approveRole", role.getId());
		User user = (User) session.getAttribute(Constants.SESSION_KEY);
		mainService.create(test, variables, user);
		return "/index";
	}

	/**
	 * 首页画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "")
	public ModelAndView index(HttpSession session) {
		User user = (User) session.getAttribute(Constants.SESSION_KEY);
		ModelAndView mav = new ModelAndView("/index");
		// List<Leave> results = workflowService.findTodoTasks(userId);
		// mav.addObject("leaves", results);
		return mav;
	}
}
