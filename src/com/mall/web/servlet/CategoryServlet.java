package com.mall.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mall.pojo.Category;
import com.mall.service.CategoryService;
import com.mall.service.impl.CategoryServiceImpl;
import com.mall.util.StringUtils;
import com.mall.util.UUIDUtils;
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
	 * desc:查询所有分类(ajax),会使用缓存
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
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月17日
	 * @version 1.0
	 * desc:后台管理系统获取分类集合方法
	 */
	public String findAllCategory(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		List<Category> list = categoryService.findAllCategory();
		request.setAttribute("list", list);
		return "/admin/category/list.jsp";
	}
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月17日
	 * @version 1.0
	 * desc:编辑分类，获取cid后查询分类跳转页面
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String cid = request.getParameter("cid");
		if (StringUtils.isEmpty(cid)) {
			return null;
		}
		
		Category category = categoryService.get(cid);
		request.setAttribute("category", category);
		return "/admin/category/edit.jsp";
	}
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月17日
	 * @version 1.0
	 * desc:更新分类
	 */
	public String save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String cid = request.getParameter("cid");
		if (StringUtils.isEmpty(cid)) {
			return null;
		}
		
		String cname = request.getParameter("cname");
		if (StringUtils.isEmpty(cname)) {
			return null;
		}
		
		Category category = new Category();
		category.setCid(cid);
		category.setCname(cname);
		categoryService.update(category);
		return "categoryServlet?method=findAllCategory";
	}
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月17日
	 * @version 1.0
	 * desc:删除分类
	 */
	public String del(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String cid = request.getParameter("cid");
		if (StringUtils.isEmpty(cid)) {
			return null;
		}
		
		categoryService.del(cid);
		return "categoryServlet?method=findAllCategory";
	}
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月17日
	 * @version 1.0
	 * desc:新增分类
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String cname = request.getParameter("cname");
		if (StringUtils.isEmpty(cname)) {
			return null;
		}
		
		Category category = new Category();
		category.setCid(UUIDUtils.ranUUID());
		category.setCname(cname);
		categoryService.add(category);
		return "categoryServlet?method=findAllCategory";
	}
}
