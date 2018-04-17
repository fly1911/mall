package com.mall.dao;

import java.util.List;

import com.mall.pojo.OrderItem;

public interface OrderItemDAO {
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0
	 * desc:add
	 */
	public void add(OrderItem orderItem) throws Exception;
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月17日
	 * @version 1.0
	 * desc:根据oid获取订单项
	 */
	public List<OrderItem> findOrderItemsByOid(String oid) throws Exception;
}
