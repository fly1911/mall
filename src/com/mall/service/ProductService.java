package com.mall.service;

import java.util.List;

import com.mall.pojo.PageBean;
import com.mall.pojo.Product;

public interface ProductService {
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
	public Product get(String pid) throws Exception ;

	/**
	 * 
	 * @author bo
	 * @date 2018年4月16日
	 * @version 1.0
	 * desc:分页查询分类商品
	 */
	public PageBean<Product> pageProduct(String cid, int page, int pageSize) throws Exception;
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月18日
	 * @version 1.0
	 * desc:后台查询分类商品
	 */
	public PageBean<Product> adminPageProduct(int page, int pageSize) throws Exception;

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
	 * desc:查询下架商品
	 */
	public List<Product> findDownProducts()  throws Exception;

}
