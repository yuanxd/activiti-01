package com.xwinter.study.activiti.web.index;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xwinter.study.activiti.common.Constants;
import com.xwinter.study.activiti.entity.identity.Role;
import com.xwinter.study.activiti.entity.identity.User;
import com.xwinter.study.activiti.service.identity.RoleService;
import com.xwinter.study.activiti.service.identity.UserService;

/**
 * 登录Controller
 * 
 * @author 袁晓冬
 * 
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	/** 用户服务 */
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	/**
	 * 初始化系统数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String init() {
		List<Role> initRoleList = new ArrayList<Role>();
		// 初始化角色
		Role adminRole = new Role();
		adminRole.setName("admin");
		initRoleList.add(adminRole);

		Role userRole = new Role();
		userRole.setName("user");
		initRoleList.add(userRole);

		Role leaderRole = new Role();
		leaderRole.setName("leader");
		initRoleList.add(leaderRole);

		Role hrRole = new Role();
		hrRole.setName("hr");
		initRoleList.add(hrRole);
		roleService.save(initRoleList);

		// 初始化用户
		List<User> initUserList = new ArrayList<User>();
		User user = new User();
		user.setName("dev");
		user.setPassword("dev");
		List<Role> userRoles = new ArrayList<Role>();
		userRoles.add(userRole);
		user.setRoles(userRoles);
		initUserList.add(user);

		User leader = new User();
		leader.setName("leader");
		leader.setPassword("leader");
		List<Role> leaderRoles = new ArrayList<Role>();
		leaderRoles.add(leaderRole);
		leader.setRoles(leaderRoles);
		initUserList.add(leader);

		User hr = new User();
		hr.setName("hr");
		hr.setPassword("hr");
		List<Role> hrRoles = new ArrayList<Role>();
		hrRoles.add(hrRole);
		hr.setRoles(hrRoles);
		initUserList.add(hr);

		User test = new User();
		test.setName("test");
		test.setPassword("test");
		test.setRoles(initRoleList);
		initUserList.add(test);

		userService.save(initUserList);
		return "/login";
	}

	/**
	 * 返回登录画面
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		return "/login";
	}

	/**
	 * 登录验证
	 * 
	 * @param userTemp
	 *            登录用户
	 * @param session
	 *            HttpSession
	 * @return
	 */
	@RequestMapping(value = "/doLogin")
	public String doLogin(User userTemp, HttpSession session) {
		User user = userService.getByCode(userTemp.getCode());
		if (null != user) {
			if (user.getPassword().equals(userTemp.getPassword())) {
				session.setAttribute(Constants.SESSION_KEY, user);
				return "redirect:/index";
			}
		}
		return "/login";
	}
}
