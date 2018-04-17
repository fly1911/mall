package com.mall.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mall.pojo.User;
import com.mall.service.UserService;
import com.mall.service.impl.UserServiceImpl;
import com.mall.util.CookieUtils;

/**
 * Servlet Filter implementation class AutoLoginFilter
 */
public class AutoLoginFilter implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		//1、判断是否是登录请求
		if ("/userServlet".equals(req.getServletPath()) && "login".equals(req.getParameter("method"))) {
			chain.doFilter(request, response);
			return;
		}
		
		//2、判断用户是否已登录
		if (session.getAttribute("user") != null) {
			chain.doFilter(request, response);
			return;
		}
		
		//3、查询是否有“userInfo”的cookie
		Cookie[] cookies = req.getCookies();
		Cookie cookie = CookieUtils.getCookieByName(cookies, "userInfo");
		//3.1没有直接放行
		if (cookie == null) {
			chain.doFilter(request, response);
			return;
		}
		//3.2 有则取出用户名和密码，进行登录(即自动登录)
		//3.2.1 用户名和密码不对不管
		//3.2.2  用户名和密码正确进行用户登录
		try {
			String[] userInfo = cookie.getValue().split("&");
			UserService userService = new UserServiceImpl();
			User user = userService.queryUser(userInfo[0], userInfo[1]);
			if (user != null) {
				user.setPassword("");
				session.setAttribute("user", user);
			}
			System.out.println("自动登录成功.....");
		} catch (Exception e) {
			System.out.println("自动登录出错.....");
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("AutoLoginFilter-->init");
	}

	@Override
	public void destroy() {
		System.out.println("AutoLoginFilter-->destroy");
		
	}

}
