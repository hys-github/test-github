package com.hys.servlet;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	//�������ģʽ
	//��̬���ұ�volatile����
	private static volatile JedisPool jedisPool=null;
	//�޲ι���
	private JedisPoolUtil() {}
	//�ⲿʹ�þ�̬��������JedisPool���ӳ�
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
