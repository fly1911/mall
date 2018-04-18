package com.mall.service;

import java.util.List;

import com.mall.pojo.OrderItem;

public interface OrderItemService {
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0
	 * desc:add
	 */
	public void add(List<OrderItem> orderItems) throws Exception;
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月18日
	 * @version 1.0
	 * desc:根据oid获取订单项集合
	 */
	public List<OrderItem> findOrderItemByOid(String oid) throws Exception;

}
