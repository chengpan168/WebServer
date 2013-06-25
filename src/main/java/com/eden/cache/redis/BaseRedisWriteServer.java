package com.eden.cache.redis;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.eden.util.JsonUtil;

public class BaseRedisWriteServer implements RedisWriteServer {

	private static Logger logger = LoggerFactory.getLogger(BaseRedisWriteServer.class);

	protected List<JedisPool> jedisWritePool;

	public static final Integer DEFAULT_EXPIRE_TIME = 60 * 60 * 24;

	protected JedisPool getWritePool() {
	   JedisPool pool = null;
		
       for (JedisPool jp : jedisWritePool) {
		try {
			Jedis jedis = null;
			jedis = jp.getResource();
			jp.returnResource(jedis);
			pool = jp;
			break;
	     } catch (Exception e) {
				  e.printStackTrace();
		    	  logger.debug("get  writepool  error...........");
		 } finally {
			 
		 }
	   }
       
       if (pool == null) {
    	   logger.debug("write pool use up....... can not get pool ");
       }
	   return pool;
	}

	public void save(String key, String value) {
		JedisPool pool = getWritePool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.set(key, value);
		} finally {
		    if (jedis != null)
			  pool.returnResource(jedis);
		}
	}

	public void save(String key, String value, int expireTime) {
		JedisPool pool = getWritePool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.set(key, value);
			if (expireTime != 0) {
				jedis.expire(key, expireTime);
			}
		} finally {
			if (jedis != null)
				pool.returnResource(jedis);
				
		}
		
		
	}

	public void save(String key, Object obj) {
		JedisPool pool = getWritePool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.set(key, object2String(obj));
		} finally {
			if (jedis != null)
			  pool.returnResource(jedis);
		}
	}

	public void save(String key, Object obj, int expireTime) {
		JedisPool pool = getWritePool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.set(key, object2String(obj));
			if (expireTime != 0) {
				jedis.expire(key, expireTime);
			}
		} finally {
			if (jedis != null)
				  pool.returnResource(jedis);
		}
	}

	public void replace(String key, Map<String, String> map, int expireTime) {
		JedisPool pool = getWritePool();
		Jedis jedis = null;
		try {
			jedis =  pool.getResource();
			jedis.del(key);
			save(key, map, expireTime);
		} finally {
			if (jedis != null)
				  pool.returnResource(jedis);
		}
	}

	public void save(String key, Map<String, String> map) {
		JedisPool pool = getWritePool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			if (map.size() == 0) {
				map.put("", "");
			}
			jedis.hmset(key, map);
		} finally {
			if (jedis != null)
				  pool.returnResource(jedis);
		}
	}

	public void save(String key, Map<String, String> map, int expireTime) {
		JedisPool pool = getWritePool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			if (map.size() == 0) {
				map.put("", "");
			}
			jedis.hmset(key, map);
			if (expireTime != 0) {
				jedis.expire(key, expireTime);
			}
		} finally {
			if (jedis != null)
				  pool.returnResource(jedis);
		}
		
	}
	
	public void saveMapVal(String cacheKey,String mapKey,String mapVal) {
		JedisPool pool = getWritePool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.hset(cacheKey, mapKey, mapVal);
		} finally {
			if (jedis != null)
				  pool.returnResource(jedis);
		}
	}

	public void del(String key) {
		JedisPool pool = getWritePool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.del(key);
		} finally {
			if (jedis != null)
				  pool.returnResource(jedis);
		}
		
	}
	
	public void del(String... keys) {
		JedisPool pool = getWritePool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.del(keys);
		} finally {
			if (jedis != null)
				  pool.returnResource(jedis);
		}
	}

	public void del(String key, String mapKey) {
		JedisPool pool = getWritePool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.hdel(key, mapKey);
		} finally {
			if (jedis != null)
				  pool.returnResource(jedis);
		}
	}

	private String object2String(Object obj) {
		return JsonUtil.toJson(obj);
	}



	public void setJedisWritePool(List<JedisPool> jedisWritePool) {
		this.jedisWritePool = jedisWritePool;
	}

	public void saveSet(String key, String str, int expireTime) {
		JedisPool pool = getWritePool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.sadd(key, str);
			if (expireTime != 0) {
				jedis.expire(key, expireTime);
			}
		} finally {
			if (jedis != null)
				  pool.returnResource(jedis);
		}
	}

	public void delFromSet(String key, String setValue) {
		JedisPool pool = getWritePool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.srem(key, setValue);
		} finally {
			if (jedis != null)
				  pool.returnResource(jedis);
		}
	}
	
	public void saveList(String key,List<String> list,int expireTime) {
		JedisPool pool = getWritePool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			for (String str : list) {
				jedis.lpush(key, str);
			}
			if (expireTime != 0) {
				jedis.expire(key, expireTime);
			}
		} finally {
			if (jedis != null)
				  pool.returnResource(jedis);
		}
	}
	
	public void saveList(String key,List<String> list) {
		saveList(key, list, 0);
	}
	
	public void lpushQueue(String key,String data) {
		JedisPool pool = getWritePool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.lpush(key, data);
		} finally {
			if (jedis != null)
				  pool.returnResource(jedis);
		}
	}
	
	public void rpushQueue(String key,String data) {
		JedisPool pool = getWritePool();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.rpush(key, data);
		} finally {
			if (jedis != null)
				  pool.returnResource(jedis);
		}
	}



}
