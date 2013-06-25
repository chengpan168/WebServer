package com.eden.cache.redis;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 主备连接测试类
 * @author Ltiebiao
 *
 */
public class RedisReplacation {
	
	public static final Logger logger = LoggerFactory.getLogger(RedisReplacation.class);
	
	public static final String REPL_KEY = "raxtone_function_replacation_key";
	public static final String REPL_VAL = "raxtone_function_replacation_value";
	
	public static final Integer LIFE_CYCLE_SECOND = 5;
	
	protected Map<String,List<JedisPool>> writePool;
	
	protected Map<String,List<JedisPool>> readPool;
	
	public Map<String, List<JedisPool>> getWritePool() {
		return writePool;
	}

	public void setWritePool(Map<String, List<JedisPool>> writePool) {
		this.writePool = writePool;
	}

	public Map<String, List<JedisPool>> getReadPool() {
		return readPool;
	}

	public void setReadPool(Map<String, List<JedisPool>> readPool) {
		this.readPool = readPool;
	}

	public void init() {
		int i = 1;
		for (Iterator<String> itr = writePool.keySet().iterator(); itr.hasNext();) {
			String key = itr.next();
		    List<JedisPool>  listPool = writePool.get(key);
		    
		    for (JedisPool wPool : listPool) {
				Jedis jedis = null;
				try {
					jedis = wPool.getResource();
					jedis.setex(REPL_KEY+i, LIFE_CYCLE_SECOND, REPL_VAL);
				} finally {
					if (jedis != null)
						 wPool.returnResource(jedis);
				}
			}
		    i++;
		}
		
		int j = 1;
		for (Iterator<String> itr = readPool.keySet().iterator(); itr.hasNext();) {
			String key = itr.next();
		    List<JedisPool>  listPool = readPool.get(key);
		    
		    for (JedisPool rPool : listPool) {
				Jedis jedis = null;
				try {
					jedis = rPool.getResource();
					String cacheVal = jedis.get(REPL_KEY+j);
					if (!REPL_VAL.equals(cacheVal))
						throw new RuntimeException("slave replacation master error............");
				} finally {
					if (jedis != null)
						rPool.returnResource(jedis);
				}
			}
		    j++;
		}
		
		logger.debug("################################################# replacation success ####################################");
	}
	
}
