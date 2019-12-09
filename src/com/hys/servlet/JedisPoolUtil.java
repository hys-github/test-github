package com.hys.servlet;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	//单列设计模式
	//静态的且被volatile修饰
	private static volatile JedisPool jedisPool=null;
	//无参构造
	private JedisPoolUtil() {}
	//外部使用静态工厂类获得JedisPool连接池
	public static JedisPool getJedisPoolInstance() {
		//
		if(jedisPool==null) {
			synchronized (JedisPoolUtil.class) {
				if(jedisPool==null) {
					JedisPoolConfig poolConfig=new JedisPoolConfig();
					poolConfig.setMaxTotal(200);
					poolConfig.setMaxIdle(32);
					poolConfig.setMaxWaitMillis(100*1000);
					poolConfig.setBlockWhenExhausted(true);
					poolConfig.setTestOnBorrow(true);
					jedisPool=new JedisPool(poolConfig,"192.168.230.128",6379,60000);
				}
			}
		}
		return jedisPool;
	}
	
}
