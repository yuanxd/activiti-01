package com.xwinter.study.activiti.web.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xwinter.study.activiti.web.BaseController;

@Controller
@RequestMapping(value = "/system/menu")
public class MenuController extends BaseController {
	/**
	 * 首页画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "")
	public String mainPage() {
		return "/system/menu";
	}
}
