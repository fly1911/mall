package com.mall.service.impl;

import java.util.List;

import com.mall.dao.ProductDAO;
import com.mall.dao.impl.ProductDAOImpl;
import com.mall.pojo.PageBean;
import com.mall.pojo.Product;
import com.mall.service.ProductService;

public class ProductServiceImpl implements ProductService {
	
	private ProductDAO productDAO = new ProductDAOImpl();

	@Override
	public List<Product> findHotProducts() throws Exception {
		List<Product> hotProducts = productDAO.findHotProducts();
		return hotProducts;
	}

	@Override
	public List<Product> findNewProducts() throws Exception {
		List<Product> newProducts = productDAO.findNewProducts();
		return newProducts;
	}

	@Override
	public Product get(String pid) throws Exception {
		Product product = productDAO.get(pid);
		return product;
	}

	@Override
	public PageBean<Product> pageProduct(String cid, int page, int pageSize) throws Exception {
		
		Long count = productDAO.count(cid);
		//查询当前页数据，需要cid,从第几条记录开始查询，查询多少条
		int start = (page-1) * pageSize;
		List<Product> data = productDAO.findListByCid(cid, start, pageSize);
		int totalPage = (int) Math.ceil(count.doubleValue()/pageSize);
		
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		pageBean.setPageSize(pageSize);
		pageBean.setData(data);
		pageBean.setTotalPage(totalPage);
		pageBean.setCount(count);
		return pageBean;
	}

}
