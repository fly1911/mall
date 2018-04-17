package com.mall.test;

import org.junit.Test;

import com.mall.util.JedisUtils;

import redis.clients.jedis.Jedis;

public class TestJedisUtils {
	Jedis jedis = JedisUtils.getJedis();
	
	@Test
	public void testSetKey() {
		jedis.set("name","tomcat");
		jedis.close();
	}
	
	@Test
	public void testGetValue(){
		System.out.println(jedis.get("name"));
		jedis.close();
	}

}
