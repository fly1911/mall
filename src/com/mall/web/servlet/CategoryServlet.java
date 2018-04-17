package com.mall.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mall.service.CategoryService;
import com.mall.service.impl.CategoryServiceImpl;
import com.mall.web.base.BaseServlet;

/**
 * Servlet implementation class CategoryServlet
 */
public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private CategoryService categoryService = new CategoryServiceImpl();

	/**
	 * 
	 * @author bo
	 * @date 2018年4月15日
	 * @version 1.0
	 * desc:查询所有分类
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public void list(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		
		List list = categoryService.list();
		Gson gson  = new Gson();
		String listStr = gson.toJson(list);
		response.getWriter().write(listStr);
	}

}
