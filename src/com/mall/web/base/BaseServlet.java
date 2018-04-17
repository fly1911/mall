package com.mall.web.base;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 覆写HttpServlet的方法，service()方法是servlet每次接受请求进行处理都要调用的一个方法
	 * 在这里创建一个servlet的基类，让所有继承此servlet的子类能根据请求参数自动找到对应的方法
	 * 还可以进行页面的跳转
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String methodName = req.getParameter("method");
			//默认执行的方法
			if ("".equals(methodName) || methodName==null) {
				methodName = "execute";
			}
			Method method = this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			String jspPath = (String) method.invoke(this, req,resp);
			System.out.println("jspPath："+jspPath);
			if (!"".equals(jspPath) && jspPath!=null) {
				req.getRequestDispatcher(jspPath).forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String execute(HttpServletRequest req, HttpServletResponse resp){
		return null;
	}
	
}
