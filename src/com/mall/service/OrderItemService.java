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

}
