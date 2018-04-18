package com.mall.dao.impl;

import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mall.dao.OrdersDAO;
import com.mall.pojo.Orders;
import com.mall.util.C3P0Util;

public class OrdersDAOImpl implements OrdersDAO {
	
	private QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());

	@Override
	public void add(Orders order) throws Exception {
		String sql = "insert into orders(oid, ordertime, total, state, address, name, telephone, uid)"
				+" values(?,?,?,?,?,?,?,?)";
		
		runner.update(sql, order.getOid(), order.getOrdertime(), order.getTotal(), 
				order.getState(), order.getAddress(), order.getName(), order.getTelephone(), order.getUid());
	}

	@Override
	public void update(Orders order) throws Exception {
		String sql = "update orders set ordertime=?, total=?, state=?, address=?, name=?, telephone=?, uid=? where oid=?" ;
		
		runner.update(sql, order.getOrdertime(), order.getTotal(), 
				order.getState(), order.getAddress(), order.getName(), order.getTelephone(), order.getUid(),order.getOid());
	}

	@Override
	public Orders get(String id) throws Exception {
		String sql = "select * from orders where oid=?";
		Orders orders = runner.query(sql, new BeanHandler<Orders>(Orders.class), id);
		return orders;
		
	}

	@Override
	public List<Orders> findUserOrders(String uid, int page, int pageSize) throws Exception {
		String sql = "select * from orders where uid=? order by ordertime desc limit ?,?";
		List<Orders> orders = runner.query(sql, new BeanListHandler<Orders>(Orders.class), uid, page, pageSize);
		return orders;
	}

	@Override
	public long findCountByUser(String uid) throws Exception {
		String sql = "select count(*) from orders where uid=?";
		long count = (long) runner.query(sql, new ScalarHandler(),uid);
		return count;
	}

	@Override
	public List<Orders> findOrderList(int...state) throws Exception {
		
		String sql = "select * from orders where state=? order by ordertime desc";
		if (state.length == 0) {
			sql = "select * from orders order by ordertime desc";
			List<Orders> orders = runner.query(sql, new BeanListHandler<Orders>(Orders.class));
			return orders;
		}
		List<Orders> orders = runner.query(sql, new BeanListHandler<Orders>(Orders.class), state[0]);
		return orders;
	}

}
