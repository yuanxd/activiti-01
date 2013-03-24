package com.xwinter.study.activiti.web.leave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xwinter.study.activiti.service.leave.LeaveService;
import com.xwinter.study.annotation.Access;

@Controller
@RequestMapping(value = "/leave")
@Access(name = "请假", code = "leave")
public class LeaveAction {
	@Autowired
	private LeaveService leaveService;
	
	@RequestMapping(value = { "apply" })
	@Access(name = "发起流程", code = "startwf")
	public String startProcess(String form) {
		System.err.println(form.toString());
		return "/oa/leave/leaveApply";
	}
}
