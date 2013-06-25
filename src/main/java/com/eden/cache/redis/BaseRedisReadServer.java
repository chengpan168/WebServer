package com.eden.cache.redis;


import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eden.util.JsonUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class BaseRedisReadServer implements RedisReadServer{

	private static Logger logger = LoggerFactory.getLogger(BaseRedisReadServer.class);

	private List<JedisPool> jedisReadPool;

	public static final Integer DEFAULT_EXPIRE_TIME = 60 * 60 * 24;

	private JedisPool getReadPool() {
		JedisPool pool = null;
		for (JedisPool jp : jedisReadPool) {
	      try {
			Jedis jedis = null;
			jedis = jp.getResource();
			jp.returnResource(jedis);
			pool = jp;
			break;
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  logger.debug("get  readpool  error...........");
	      } finally {
	    	  
	      }
		}
		if (pool == null) {
			logger.debug("read pool use up....... can not get pool ");
		}
		return pool;
	}

	public List<String> get(String key, String mapKey) {
		JedisPool pool = getReadPool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			List<String> list = jedis.hmget(key, mapKey);
 			return list;
		} finally {
			if (jedis != null)
		    	pool.returnResource(jedis);
		}
		
	}
	
	public List<String> get(String key, String[] mapKeys) {
		JedisPool pool = getReadPool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			List<String> list = jedis.hmget(key, mapKeys);
 			return list;
		} finally {
			if (jedis != null)
		    	pool.returnResource(jedis);
		}
		
	}

	public String get(String key) {
		JedisPool pool = getReadPool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			String str = jedis.get(key);
			return str;
		} finally {
			if (jedis != null)
			  pool.returnResource(jedis);
		}
	}
	
	public <T> T get(String key,Class<T> clazz) {
		JedisPool pool = getReadPool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return JsonUtil.fromJson(jedis.get(key), clazz);
		} finally {
			if (jedis != null)
			  pool.returnResource(jedis);
		}
	}
	

	public Set<String> getMapKeys(String key) {
		JedisPool pool = getReadPool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			Set<String> set = jedis.hkeys(key);
			return set;
		} finally {
			if (jedis != null)
			  pool.returnResource(jedis);
		}
	}
	
	
	public Set<String> getKeysByPattern(String patternKey) {
		JedisPool pool = getReadPool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			Set<String> set = jedis.keys(patternKey);
			return set;
		} finally {
			if (jedis != null)
			  pool.returnResource(jedis);
		}
	}

	public Map<String, String> getMap(String key) {
		JedisPool pool = getReadPool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			Map<String, String> resMap = jedis.hgetAll(key);
			return resMap;
		} finally {
			if (jedis != null)
			  pool.returnResource(jedis);
		}

	}

	public List<String> getMapVal(String key) {
		JedisPool pool = getReadPool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			List<String> list = jedis.hvals(key);
			return list;
		} finally {
			if (jedis != null)
				  pool.returnResource(jedis);
		}
	}
	
	public String getMapVal(String cacheKey,String mapKey) {
		JedisPool pool = getReadPool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			String mapVal = jedis.hget(cacheKey, mapKey);
			return mapVal;
		} finally {
			if (jedis != null)
				  pool.returnResource(jedis);
		}
	}

	public boolean isExists(String key) {
		JedisPool pool = getReadPool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			boolean flag = jedis.exists(key);
			return flag;
		} finally {
			if (jedis != null)
			  pool.returnResource(jedis);
		}
	}

	public boolean isExists(byte[] byteKey) {
		JedisPool pool = getReadPool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			boolean flag = jedis.exists(byteKey);
			return flag;
		} finally {
		    if (jedis != null)
			  pool.returnResource(jedis);
		}
	}

	public void setJedisReadPool(List<JedisPool> jedisReadPool) {
		this.jedisReadPool = jedisReadPool;
	}
	
	public Set<String> getSet(String key) {
		JedisPool pool = getReadPool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			Set<String> str = jedis.smembers(key);
			return str;
		} finally {
			if (jedis != null)
			  pool.returnResource(jedis);
		}
	}
	
	public List<String> getList(String key) {
		JedisPool pool = getReadPool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.lrange(key, 0, -1);
		} finally {
			if (jedis != null)
			  pool.returnResource(jedis);
		}
	}
	
	public String brpopDataQueue(String key) {
		JedisPool pool = getReadPool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.brpop(0, key).get(1);
		} finally {
			if (jedis != null)
			  pool.returnResource(jedis);
		}
	}
	
	public String blpopDataQueue(String key) {
		JedisPool pool = getReadPool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.blpop(0, key).get(1);
		} finally {
			if (jedis != null)
			  pool.returnResource(jedis);
		}
	}
	
	public long llenQueueSize(String key) {
		JedisPool pool = getReadPool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.llen(key);
		} finally {
			if (jedis != null)
			  pool.returnResource(jedis);
		}
	}

}
