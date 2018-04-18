package com.mall.web.servlet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mall.pojo.CartItem;
import com.mall.pojo.OrderItem;
import com.mall.pojo.Orders;
import com.mall.pojo.PageBean;
import com.mall.pojo.Product;
import com.mall.pojo.User;
import com.mall.service.OrderItemService;
import com.mall.service.OrdersService;
import com.mall.service.ProductService;
import com.mall.service.impl.OrderItemServiceImpl;
import com.mall.service.impl.OrdersServiceImpl;
import com.mall.service.impl.ProductServiceImpl;
import com.mall.util.UUIDUtils;
import com.mall.web.base.BaseServlet;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private OrdersService ordersService = new OrdersServiceImpl();
	private OrderItemService orderItemService= new OrderItemServiceImpl();
	private ProductService productService = new ProductServiceImpl();

	/**
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0 desc:生成订单
	 */
	@SuppressWarnings("unchecked")
	public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// 未登录
		if (user == null) {
			request.setAttribute("msg", "请登录后操作");
			return "/jsp/login.jsp";
		}

		Orders orders = new Orders();
		orders.setOid(UUIDUtils.ranUUID());
		orders.setOrdertime(new Date());
		orders.setState(1);
		orders.setUid(user.getUid());
		
		List<CartItem> items = (List<CartItem>) session.getAttribute(CartServlet.CART_SESSION_NAME);
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		if (items!=null && items.size() != 0) {
			double total = 0d;
			for (CartItem cartItem : items) {
				OrderItem orderItem = new OrderItem();
				orderItem.setItemid(UUIDUtils.ranUUID());
				orderItem.setCount(cartItem.getCount());
				orderItem.setSubtotal(cartItem.getSubtotal());
				Product product = productService.get(cartItem.getPid());
				orderItem.setProduct(product);
				orderItem.setOid(orders.getOid());
				orderItems.add(orderItem);
				total += cartItem.getSubtotal();
			}
			orders.setTotal(total);
		}
		
		ordersService.add(orders);
		orderItemService.add(orderItems);
		
		request.setAttribute("orders", orders);
		request.setAttribute("orderItems", orderItems);
		
		//清空购物车
		session.removeAttribute(CartServlet.CART_SESSION_NAME);
		return "/jsp/order_info.jsp";
	}
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0
	 * desc:更新订单
	 */
	public String updateOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		String oid = request.getParameter("oid");
		String address = new String(request.getParameter("address").getBytes("ISO-8859-1"),"utf-8");
		String name = request.getParameter("name");
		String telephone = request.getParameter("telephone");
		System.out.println("address:"+address);
		System.out.println("name:"+name);
		System.out.println("telephone:"+telephone);
		
		if (!"".equals(oid) && oid != null) {
			Orders orders = ordersService.get(oid);
			orders.setAddress(address);
			orders.setName(name);
			orders.setTelephone(telephone);
			ordersService.update(orders);
		}
		return "orderServlet?method=findUserOrder";
	}
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月17日
	 * @version 1.0
	 * desc:根据用户id获取订单
	 */
	public String findUserOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// 未登录
		if (user == null) {
			request.setAttribute("msg", "请登录后操作");
			return "/jsp/login.jsp";
		}
		
		String pageStr = request.getParameter("page");
		int page = 1;
		if(pageStr!=null && !"".equals(pageStr)){
			page = Integer.parseInt(pageStr);
		}
		
		String pageSizeStr = request.getParameter("pageSize");
		int pageSize = 5;
		if(pageSizeStr!=null && !"".equals(pageSizeStr)){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		
		String uid = user.getUid();
		PageBean<Orders> pageBean = ordersService.findUserOrders(uid, page, pageSize);
		request.setAttribute("page", pageBean);
		return "/jsp/order_list.jsp";
	}
}
