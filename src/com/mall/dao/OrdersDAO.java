package com.mall.dao;

import java.util.List;

import com.mall.pojo.Orders;

public interface OrdersDAO {
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0
	 * desc:add
	 */
	public void add(Orders order) throws Exception ;
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0
	 * desc:update
	 */
	public void update(Orders order) throws Exception;
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0
	 * desc:get
	 */
	public Orders get(String id) throws Exception;


	/**
	 * 
	 * @author bo
	 * @date 2018年4月17日
	 * @version 1.0
	 * desc:用户订单集合
	 */
	public List<Orders> findUserOrders(String uid, int page, int pageSize) throws Exception;
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月17日
	 * @version 1.0
	 * desc:用户订单数量
	 */
	public long findCountByUser(String uid) throws Exception;

	/**
	 * 
	 * @author bo
	 * @date 2018年4月18日
	 * @version 1.0
	 * desc:根据订单状态获取订单集合
	 */
	public List<Orders> findOrderList(int...state) throws Exception;

}
