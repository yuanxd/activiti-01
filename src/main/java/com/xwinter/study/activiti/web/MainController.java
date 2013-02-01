package com.xwinter.study.activiti.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xwinter.study.activiti.common.AppException;
import com.xwinter.study.activiti.common.Constants;
import com.xwinter.study.activiti.entity.Test;
import com.xwinter.study.activiti.entity.User;
import com.xwinter.study.activiti.service.MainService;

/**
 * 首页Controller
 * 
 * @author 袁晓冬
 * 
 */
@Controller
@RequestMapping(value = "/index")
public class MainController {
	@Autowired
	private MainService testService;

	@RequestMapping(value = "start", method = RequestMethod.POST)
	public String start(Test test, RedirectAttributes redirectAttributes,
			HttpSession session) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("approveRole", "3fdf0dbd-decd-49cd-81e0-ef4e5eae2b1e");
		testService.create(test, variables,
				(User) session.getAttribute(Constants.SESSION_KEY));
		return "/index";
	}

	/**
	 * 首页画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "")
	public String index() {
		return "/index";
	}
}
