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

@Controller
@RequestMapping(value = "/test")
public class TestController {
	@Autowired
	private MainService testService;

	@RequestMapping(value = "/index")
	public String index() {
		return "/test/index";
	}

	@RequestMapping(value = "start", method = RequestMethod.POST)
	public String start(Test test, RedirectAttributes redirectAttributes,
			HttpSession session) {
		if (!StringUtils.hasLength(test.getId())) {
			throw new AppException("please do save first!");
		}
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("approve", "leader");
		testService.create(test, variables,
				(User) session.getAttribute(Constants.SESSION_KEY));
		return "/test/index";
	}
}