package com.xwinter.study.activiti.web.index;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xwinter.study.activiti.common.GlobalData;
import com.xwinter.study.activiti.common.OperateImage;
import com.xwinter.study.activiti.common.Utils;
import com.xwinter.study.activiti.entity.Leave;
import com.xwinter.study.activiti.entity.identity.Role;
import com.xwinter.study.activiti.entity.identity.User;
import com.xwinter.study.activiti.service.identity.RoleService;
import com.xwinter.study.activiti.service.index.MainService;

/**
 * 首页Controller
 * 
 * @author 袁晓冬
 * 
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {
	// @Autowired
	// private MainService testService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MainService mainService;

	@RequestMapping(value = "start", method = RequestMethod.POST)
	public String start(Leave test, RedirectAttributes redirectAttributes,
			HttpSession session) {
		Map<String, Object> variables = new HashMap<String, Object>();
		Role role = roleService.getByName("leader");
		variables.put("approveRole", role.getId());
		User user = (User) session.getAttribute(GlobalData.USER_SESSION_KEY);
		mainService.create(test, variables, user.getId());
		return "/index";
	}

	/**
	 * 首页画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "")
	public String home() {
		return "/home";
	}

	/**
	 * 返回裁剪后的图片
	 */
	@RequestMapping("crop")
	public void getCropImage(OperateImage omg, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String path = omg.getSrcpath();
		if (Utils.isEmpty(path)) {
			return;
		}
		if (path.startsWith(request.getContextPath())) {
			path = path.substring(request.getContextPath().length());
		}
		omg.setSrcpath(request.getSession().getServletContext()
				.getRealPath(path));
		OutputStream stream = response.getOutputStream();
		omg.cut(stream);
		stream.flush();
		stream.close();
	}
}
