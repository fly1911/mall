package com.mall.dao;

import java.util.List;

import com.mall.pojo.Product;

public interface ProductDAO {
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0
	 * desc:热门商品
	 */
	public List<Product> findHotProducts() throws Exception;
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0
	 * desc:最新商品
	 */
	public List<Product> findNewProducts() throws Exception;

	/**
	 * 
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0
	 * desc:获取单个商品
	 */
	public Product get(String pid) throws Exception;

	/**
	 * 
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0
	 * desc:根据cid查询总记录数
	 */
	public long count(String cid) throws Exception;

	/**
	 * 
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0
	 * desc:根据cid查询当前页数据
	 */
	public List<Product> findListByCid(String cid, int start, int pageSize) throws Exception;

	/**
	 * 
	 * @author bo
	 * @date 2018年4月18日
	 * @version 1.0
	 * desc:后台获取商品总条数
	 */
	public long findCountByPage() throws Exception;

	/**
	 * 
	 * @author bo
	 * @date 2018年4月18日
	 * @version 1.0
	 * desc:后台获取商品分页当前页数据
	 */
	public List<Product> findListByPage(int page, int pageSize) throws Exception;

	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月18日
	 * @version 1.0
	 * desc:update
	 */
	public void update(Product product) throws Exception;

	/**
	 * 
	 * @author bo
	 * @date 2018年4月18日
	 * @version 1.0
	 * desc:add
	 */
	public void add(Product product) throws Exception;

	/**
	 * 
	 * @author bo
	 * @date 2018年4月18日
	 * @version 1.0
	 * desc:上下架
	 */
	public void changeShelve(String pid, int pflag) throws Exception;

	/**
	 * 
	 * @author bo
	 * @date 2018年4月18日
	 * @version 1.0
	 * desc:下架商品
	 */
	public List<Product> findDownProducts() throws Exception;

}
