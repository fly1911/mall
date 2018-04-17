package com.mall.service.impl;

import java.util.List;

import com.mall.dao.OrderItemDAO;
import com.mall.dao.impl.OrderItemDAOImpl;
import com.mall.pojo.OrderItem;
import com.mall.service.OrderItemService;

public class OrderItemServiceImpl implements OrderItemService {
	
	private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

	@Override
	public void add(List<OrderItem> orderItems) throws Exception {
		for (OrderItem orderItem : orderItems) {
			orderItemDAO.add(orderItem);
		}
	}

}
