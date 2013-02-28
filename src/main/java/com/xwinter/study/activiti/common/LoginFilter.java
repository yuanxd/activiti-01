package com.xwinter.study.activiti.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xwinter.study.activiti.entity.identity.User;

/**
 * 安全认证过滤器
 * 
 * @author 袁晓冬
 * 
 */
public class LoginFilter implements Filter {
	/**
	 * 初始化
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * 执行过滤
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if ((request instanceof HttpServletRequest)) {
			HttpServletRequest httpReq = (HttpServletRequest) request;
			HttpServletResponse httpResp = (HttpServletResponse) response;
			// 如果访问了检查类的资源
			if (!Constants.UNCHECKURI_PATTERN.matcher(httpReq.getRequestURI())
					.matches()) {
				// 认证失败时跳转到登陆页面，并结束执行
				if (!this.checkSession(httpReq, httpResp)) {
					toLoginPage(httpReq, httpResp);
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * 检查是否已登陆
	 * 
	 * @param httpReq
	 * @param httpResp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	private boolean checkSession(HttpServletRequest httpReq,
			HttpServletResponse httpResp) throws ServletException, IOException {
		HttpSession session = httpReq.getSession();
		User loginUser = (User) session.getAttribute(Constants.SESSION_KEY);
		return null != loginUser;
	}

	/**
	 * 把请求url保存到登陆页面，跳转至登陆页面
	 * 
	 * @param httpReq
	 * @param httpResp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void toLoginPage(HttpServletRequest httpReq,
			HttpServletResponse httpResp) throws ServletException, IOException {
		httpResp.sendRedirect(httpReq.getContextPath() + "/login");
	}

	public void destroy() {
	}

}
