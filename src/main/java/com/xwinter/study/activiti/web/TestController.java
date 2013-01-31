package com.xwinter.study.activiti.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xwinter.study.activiti.entity.Test;
import com.xwinter.study.activiti.service.TestService;

@Controller
@RequestMapping(value = "/test")
public class TestController {
	@Autowired
	private TestService testService;

	@RequestMapping(value = "/index")
	public String index() {
		return "/test/index";
	}

	@RequestMapping(value = "start", method = RequestMethod.POST)
	public String start(Test test, RedirectAttributes redirectAttributes,
			HttpSession session) {
		testService.startWorkflow(test);
		return "/test/index";
	}

	@RequestMapping(value = "todo")
	public String findTodoList(String username) {
		List<Test> testList = testService.getTodoList(username);
		for (Test test : testList) {
			System.err.println(test.getName());
		}
		return "/test/index";
	}
}
