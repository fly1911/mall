package com.mall.dao.impl;

import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import com.mall.dao.ProductDAO;
import com.mall.pojo.Product;
import com.mall.util.C3P0Util;

public class ProductDAOImpl implements ProductDAO {
	
	QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());

	@Override
	public List<Product> findHotProducts() throws Exception {
		String sql = "select * from product where pflag =0 order by is_hot desc limit 9;";
		List<Product> hotProducts = runner.query(sql, new BeanListHandler<Product>(Product.class));
		return hotProducts;
	}

	@Override
	public List<Product> findNewProducts() throws Exception {
		String sql = "select * from product where pflag =0 order by pdate desc limit 9;";
		List<Product> newProducts = runner.query(sql, new BeanListHandler<Product>(Product.class));
		return newProducts;
	}

	@Override
	public Product get(String pid) throws Exception{
		String sql = "select * from product where pid=?";
		Product product = runner.query(sql, new BeanHandler<Product>(Product.class),pid);
		return product;
	}

	@Override
	public long count(String cid) throws Exception {
		String sql = "select count(*) from product where cid=? and pflag=0";
		long count = (long) runner.query(sql, new ScalarHandler(),cid);
		return count;
	}

	@Override
	public List<Product> findListByCid(String cid, int start, int pageSize) throws Exception{
		String sql = "select * from product where cid=? and pflag=0 order by pdate limit ?,?";
		List<Product> data = runner.query(sql, new BeanListHandler<Product>(Product.class), cid, start, pageSize);
		return data;
	}

	@Override
	public long findCountByPage() throws Exception {
		String sql = "select count(*) from product where pflag=0";
		long count = (long) runner.query(sql, new ScalarHandler());
		return count;
	}

	@Override
	public List<Product> findListByPage(int start, int pageSize) throws Exception {
		String sql = "select * from product where pflag=0 order by pdate desc, is_hot desc limit ?,?";
		List<Product> data = runner.query(sql, new BeanListHandler<Product>(Product.class), start, pageSize);
		return data;
	}

	@Override
	public void update(Product product) throws Exception {
		String sql = "update product set pname=?,is_hot=?,market_price=?,shop_price=?,pimage=?,cid=?,pdesc=? where pid=?";
		runner.update(sql, product.getPname(), product.getIs_hot(), product.getMarket_price(), 
				product.getShop_price(), product.getPimage(), product.getCid(), product.getPdesc(), product.getPid());
	}

	@Override
	public void add(Product product) throws Exception {
		String sql = "insert into product(pid, pname, market_price, shop_price, pimage, "
				+ "pdate, is_hot, pdesc, pflag, cid) "
				+ "values (?,?,?,?,?,?,?,?,?,?)";
		runner.update(sql, product.getPid(), product.getPname(), product.getMarket_price(), product.getShop_price(), 
				product.getPimage(), product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(), product.getCid());
	}

	@Override
	public void changeShelve(String pid, int pflag) throws Exception {
		String sql = "update product set pflag = ? where pid = ?";
		runner.update(sql, pflag, pid);
	}

	@Override
	public List<Product> findDownProducts() throws Exception {
		String sql = "select * from product where pflag=1 order by pdate desc";
		List<Product> list = runner.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}
}
