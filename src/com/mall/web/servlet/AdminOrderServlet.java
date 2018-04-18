package com.mall.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mall.pojo.OrderItem;
import com.mall.pojo.Orders;
import com.mall.service.OrderItemService;
import com.mall.service.OrdersService;
import com.mall.service.impl.OrderItemServiceImpl;
import com.mall.service.impl.OrdersServiceImpl;
import com.mall.util.StringUtils;
import com.mall.web.base.BaseServlet;

public class AdminOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrdersService ordersService = new OrdersServiceImpl();
	private OrderItemService orderItemService= new OrderItemServiceImpl();

	/**
	 * 
	 * @author bo
	 * @date 2018年4月18日
	 * @version 1.0
	 * desc:查询订单列表
	 */
	public String findOrderList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1未付款，2未发货，3未签收，4已完成
		String stateStr = request.getParameter("state");
		List<Orders> list = null;
		
		if (!StringUtils.isEmpty(stateStr)) {
			list = ordersService.findOrderList(Integer.parseInt(stateStr));
		} else {
			list = ordersService.findOrderList();
		}
		request.setAttribute("list", list);
		return "/admin/order/list.jsp";
	}

	/**
	 * 
	 * @author bo
	 * @date 2018年4月18日
	 * @version 1.0
	 * desc:获取订单详情(ajax)
	 */
	public void getOrderItemsByOid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		
		String oid = request.getParameter("oid");
		List<OrderItem> list = orderItemService.findOrderItemByOid(oid);
		response.getWriter().write(new Gson().toJson(list));
	}
}
