package com.mall.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.mall.pojo.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String servletPath = req.getServletPath();
		if ("/admin/index.jsp".equals(servletPath)) {
			chain.doFilter(request, response);
			return;
		}
		
		User user = (User) req.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("msg","请登录后访问");
			request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
			return;
		}
		if ("1".equals(user.getType())) {
			request.setAttribute("msg","请使用后台管理员的账号登录");
			request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
