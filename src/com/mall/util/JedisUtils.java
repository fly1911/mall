package com.mall.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {
	private static JedisPool jedisPool;
	private static JedisPoolConfig jedisPoolConfig;
	private static String REDIS_HOST = "192.168.10.128";
	private static int REDIS_PORT = 6379;
	
	
	static {
		jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(30);
		jedisPoolConfig.setMaxIdle(10);
		jedisPool = new JedisPool(jedisPoolConfig, REDIS_HOST, REDIS_PORT);
	}

	public static Jedis getJedis() {
		return jedisPool.getResource();
	}
}
