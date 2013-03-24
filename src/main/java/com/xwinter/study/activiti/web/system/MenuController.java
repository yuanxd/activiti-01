package com.xwinter.study.activiti.web.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xwinter.study.activiti.web.BaseController;
import com.xwinter.study.annotation.Access;

@Controller
@RequestMapping(value = "/system/menu")
@Access(name = "菜单", code = "menu")
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
