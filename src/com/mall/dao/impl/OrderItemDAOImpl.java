package com.mall.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mall.dao.OrderItemDAO;
import com.mall.pojo.OrderItem;
import com.mall.util.C3P0Util;

public class OrderItemDAOImpl implements OrderItemDAO {
	
	private QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());
	
	@Override
	public void add(OrderItem orderItem) throws Exception {
		String sql = "insert into orderitem(itemid, count, subtotal, pid, oid) values(?, ?, ?, ?, ?)";
		runner.update(sql, orderItem.getItemid(), orderItem.getCount(), orderItem.getSubtotal(),
				orderItem.getProduct().getPid(), orderItem.getOid());
	}

	@Override
	public List<OrderItem> findOrderItemsByOid(String oid) throws Exception {
		String sql = "select * from orderitem where oid=?";
		List<OrderItem> orderItems = runner.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), oid);
		return orderItems;
	}

}
