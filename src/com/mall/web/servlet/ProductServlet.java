package com.mall.web.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mall.pojo.PageBean;
import com.mall.pojo.Product;
import com.mall.service.ProductService;
import com.mall.service.impl.ProductServiceImpl;
import com.mall.util.CookieUtils;
import com.mall.web.base.BaseServlet;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService productService = new ProductServiceImpl();
	
	private static final String PRODUCT_HISOTRY_COOKIE_NAME = "pidCookie";
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0
	 * desc:查询首页热门和最新商品
	 */
	public String findIndexProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Product> hotProducts = productService.findHotProducts();
		List<Product> newProducts = productService.findNewProducts();
		
		request.setAttribute("hotProducts", hotProducts);
		request.setAttribute("newProducts", newProducts);
		return "/jsp/index.jsp";
	}
	
	/**
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0
	 * desc:获取单个商品
	 */
	public String get(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid = request.getParameter("pid");
		if ("".equals(pid) || pid == null) {
			return null;
		}
		Product product = productService.get(pid);
		request.setAttribute("product", product);
		
		//为历史记录做处理
		Cookie rs = CookieUtils.getCookieByName(request.getCookies(), PRODUCT_HISOTRY_COOKIE_NAME);
		if (rs != null) {
			//有cookie
			String value = rs.getValue();
			if (value!=null) {
				String afterAddPid = new StringBuilder(value).append(",").append(pid).toString();
				Cookie pidCookie = new Cookie(PRODUCT_HISOTRY_COOKIE_NAME,afterAddPid);
				pidCookie.setMaxAge(60*60*24*7);
				pidCookie.setPath("/");
				response.addCookie(pidCookie);
			}
		} else {
			Cookie pidCookie = new Cookie(PRODUCT_HISOTRY_COOKIE_NAME,pid);
			pidCookie.setMaxAge(60*60*24*7);
			pidCookie.setPath("/");
			response.addCookie(pidCookie);
		}
		return "/jsp/product_info.jsp";
	}
	
	/**
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0
	 * desc:分页查询商品
	 * @throws Exception 
	 */
	public String pageProduct(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//1、商品分类id,获取页码和每页显示记录数
		String cid = request.getParameter("cid");
		if(cid==null || "".equals(cid)){
			return null;
		}
		
		String pageStr = request.getParameter("page");
		int page = 1;
		if(pageStr!=null && !"".equals(pageStr)){
			page = Integer.parseInt(pageStr);
		}
		
		String pageSizeStr = request.getParameter("pageSize");
		int pageSize = 12;
		if(pageSizeStr!=null && !"".equals(pageSizeStr)){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		
		PageBean<Product> pageBean = productService.pageProduct(cid, page, pageSize);
		request.setAttribute("page", pageBean);
		request.setAttribute("cid", cid);
		System.out.println(pageBean);
		return "/jsp/product_list.jsp";
	}
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0
	 * desc:获取商品浏览记录的json数据
	 */
	public void doProductHistory(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		
		List<Product> list = new ArrayList<Product>();
		Cookie pidCookie = CookieUtils.getCookieByName(request.getCookies(), PRODUCT_HISOTRY_COOKIE_NAME);
		if (pidCookie !=null) {
			String value = pidCookie.getValue();
			if (value!=null) {
				String[] pidArr = value.split(",");
				for (int i = 0;i < pidArr.length;i++) {
					String pid = pidArr[i];
					Product product = productService.get(pid);
					list.add(product);
				}
			}
		}
		String historyProductStr = new Gson().toJson(list);
		response.getWriter().write(historyProductStr);
	}
}
