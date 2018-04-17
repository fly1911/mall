package com.mall.service.impl;

import java.util.List;

import com.mall.dao.OrderItemDAO;
import com.mall.dao.OrdersDAO;
import com.mall.dao.ProductDAO;
import com.mall.dao.impl.OrderItemDAOImpl;
import com.mall.dao.impl.OrdersDAOImpl;
import com.mall.dao.impl.ProductDAOImpl;
import com.mall.pojo.OrderItem;
import com.mall.pojo.Orders;
import com.mall.pojo.PageBean;
import com.mall.service.OrdersService;

public class OrdersServiceImpl implements OrdersService {
	
	private OrdersDAO ordersDAO = new OrdersDAOImpl();
	private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
	private ProductDAO productDAO = new ProductDAOImpl();

	@Override
	public void add(Orders order) throws Exception {
		ordersDAO.add(order);
	}

	@Override
	public void update(Orders order) throws Exception {
		ordersDAO.update(order);
	}

	@Override
	public Orders get(String id) throws Exception {
		Orders orders = ordersDAO.get(id);
		return orders;
	}

	@Override
	public PageBean<Orders> findUserOrders(String uid, int page, int pageSize) throws Exception {
		
		Long count = ordersDAO.findCountByUser(uid);
		int start = (page-1) * pageSize;
		List<Orders> orders =  ordersDAO.findUserOrders(uid, start, pageSize);
		for (Orders o : orders) {
			List<OrderItem> itemsByOid = orderItemDAO.findOrderItemsByOid(o.getOid());
			for (OrderItem orderItem : itemsByOid) {
				orderItem.setProduct(productDAO.get(orderItem.getPid()));
			}
			o.setItems(itemsByOid);
		}
		
		int totalPage = (int) Math.ceil(count.doubleValue()/pageSize);
		
		PageBean<Orders> pageBean = new PageBean<Orders>();
		pageBean.setPage(page);
		pageBean.setPageSize(pageSize);
		pageBean.setData(orders);
		pageBean.setTotalPage(totalPage);
		pageBean.setCount(count);
		return pageBean;
	}
}
