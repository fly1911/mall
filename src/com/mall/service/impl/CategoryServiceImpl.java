package com.mall.service.impl;

import java.util.List;
import com.google.gson.Gson;
import com.mall.dao.CategoryDAO;
import com.mall.dao.impl.CategoryDAOImpl;
import com.mall.service.CategoryService;
import com.mall.util.JedisUtils;

import redis.clients.jedis.Jedis;

public class CategoryServiceImpl implements CategoryService {
	
	private CategoryDAO categoryDAO = new CategoryDAOImpl();
	private static String CATEGORY_CACHE_KEY = "category_list";
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List list() throws Exception {
		
		List categoryList = null;
		Gson gson = new Gson();
		//查询缓存有无数据
		Jedis jedis = JedisUtils.getJedis();
		String categoryListStr = jedis.get(CATEGORY_CACHE_KEY);
		//1)有，把缓存中数据转换成对象后返回
		if (!"".equals(categoryListStr) && categoryListStr!=null) {
			categoryList = gson.fromJson(categoryListStr, List.class);
			System.out.println("获取缓存数据...");
			jedis.close();
			return categoryList;
		}
		//2)没有，从数据库从查询，放到缓存中，返回从数据库查询的数据
		categoryList = categoryDAO.list();
		jedis.set(CATEGORY_CACHE_KEY, gson.toJson(categoryList));
		jedis.close();
		System.out.println("从数据库查询...");
		return categoryList;
	}

}
