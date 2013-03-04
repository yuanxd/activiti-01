package com.xwinter.study.activiti.common;

import javax.servlet.http.HttpSession;

import com.xwinter.study.activiti.entity.identity.User;

public class Utils {
	/**
	 * 从Session获取当前用户信息
	 * 
	 * @param session
	 * @return
	 */
	public static User getUserFromSession(HttpSession session) {
		Object attribute = session.getAttribute(Constants.SESSION_KEY);
		return attribute == null ? null : (User) attribute;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param s
	 *            s
	 * @return boolean
	 */
	public static boolean isEmpty(String s) {
		if (null != s && !"".equals(s.trim()) && !"null".equals(s.trim())) {
			return false;
		}
		return true;
	}
}
