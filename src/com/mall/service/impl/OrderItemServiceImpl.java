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
import com.mall.service.OrderItemService;

public class OrderItemServiceImpl implements OrderItemService {
	
	private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
	private ProductDAO productDAO = new ProductDAOImpl();

	@Override
	public void add(List<OrderItem> orderItems) throws Exception {
		for (OrderItem orderItem : orderItems) {
			orderItemDAO.add(orderItem);
		}
	}

	@Override
	public List<OrderItem> findOrderItemByOid(String oid) throws Exception {
		List<OrderItem> items = orderItemDAO.findOrderItemsByOid(oid);
		for (OrderItem orderItem : items) {
			orderItem.setProduct(productDAO.get(orderItem.getPid()));
		}
		return items;
	}
	
}
