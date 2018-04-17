package com.mall.web.servlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mall.pojo.CartItem;
import com.mall.pojo.Product;
import com.mall.service.ProductService;
import com.mall.service.impl.ProductServiceImpl;
import com.mall.util.UUIDUtils;
import com.mall.web.base.BaseServlet;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	public  static final String CART_SESSION_NAME = "session_cart";
	private ProductService productService = new ProductServiceImpl();

	/**
	 * 
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0 desc:添加商品到购物车
	 */
	public void addProductToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 结果：1成功，2未登录，3失败
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		String rs = "3";

		// 没有参数
		String pid = request.getParameter("pid");
		String count = request.getParameter("count");
		if ("".equals(pid) || pid == null || "".equals(count) || count == null) {
			writer.write(rs);
			return;
		}

		// 未登录
		if (session.getAttribute("user") == null) {
			rs = "2";
			writer.write(rs);
			return;
		}

		CartItem cartItem = new CartItem();
		cartItem.setId(UUIDUtils.ranUUID());
		cartItem.setPid(pid);
		cartItem.setCount(Integer.parseInt(count));

		// 判断之前是否有添加过
		List<CartItem> items = (List<CartItem>) session.getAttribute(CART_SESSION_NAME);
		// 1)有添加过，把session里面的数据取出来，添加这条记录，再次设置
		if (items != null && items.size() != 0) {
			// 遍历items,查找是否已经添加过该商品
			for (CartItem cartI : items) {
				if (cartI.getPid().equals(cartItem.getPid())) {
					cartI.setCount(cartI.getCount() + cartItem.getCount());
					rs = "1";
					writer.write(rs);
					return;
				}
			}
			items.add(cartItem);
			session.setAttribute(CART_SESSION_NAME, items);
		} else {
			// 2)没有添加过，直接把这条记录设置到session中
			items = new ArrayList<CartItem>();
			items.add(cartItem);
			session.setAttribute(CART_SESSION_NAME, items);
		}
		rs = "1";
		writer.write(rs);
	}

	/**
	 * 
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0 desc:购物车列表
	 */
	public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<CartItem> items = (List<CartItem>) request.getSession().getAttribute(CART_SESSION_NAME);
		if (items == null || items.size() == 0) {
			return "/jsp/cart.jsp";
		}

		double totalPrice = 0d;
		for (CartItem cartItem : items) {
			if (cartItem != null) {
				Product product = productService.get(cartItem.getPid());
				if (product != null) {
					cartItem.setPimage(product.getPimage());
					cartItem.setPname(product.getPname());
					cartItem.setPrice(product.getShop_price());
					cartItem.setSubtotal(cartItem.getCount() * cartItem.getPrice());
				}
				totalPrice += cartItem.getSubtotal();
			}
		}

		request.setAttribute("cartList", items);
		request.setAttribute("totalPrice", totalPrice);
		return "/jsp/cart.jsp";
	}

	/**
	 * 
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0 desc:删除购物车项
	 */
	public void delCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		String rs = "0";

		String strId = request.getParameter("id");
		if (strId == null || "".equals(strId)) {
			writer.write(rs);
			return;
		}

		List<CartItem> items = (List<CartItem>) request.getSession().getAttribute(CART_SESSION_NAME);
		if (items != null && items.size() != 0) {
			Iterator<CartItem> iterator = items.iterator();
			while (iterator.hasNext()) {
				CartItem item = iterator.next();
				if (item.getId().equals(strId)) {
					iterator.remove();
				}
			}
		}
		request.getSession().setAttribute(CART_SESSION_NAME, items);
		rs = "1";
		writer.write(rs);
	}

	/**
	 * 
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0 desc:清空购物车
	 */
	public void clearCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().removeAttribute(CART_SESSION_NAME);
		response.sendRedirect("jsp/cart.jsp");
	}

	/**
	 * 
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0 desc:更新购物车
	 */
	public void updateCart(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 结果：1成功，2未登录，3失败
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		String rs = "3";

		// 没有参数
		String id = request.getParameter("id");
		String count = request.getParameter("count");
		if ("".equals(id) || id == null || "".equals(count) || count == null) {
			writer.write(rs);
			return;
		}

		// 未登录
		if (session.getAttribute("user") == null) {
			rs = "2";
			writer.write(rs);
			return;
		}

		List<CartItem> items = (List<CartItem>) session.getAttribute(CART_SESSION_NAME);
		for (CartItem cartItem : items) {
			if (cartItem.getId().equals(id)) {
				cartItem.setCount(Integer.parseInt(count));
			}
		}
		
		rs = "1";
		writer.write(rs);
	}
}