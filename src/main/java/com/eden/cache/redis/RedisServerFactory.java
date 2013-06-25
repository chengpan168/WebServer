package com.eden.cache.redis;


import java.util.Map;

public class RedisServerFactory {

	
	private Map<String,?> redisMap;
	
	public RedisReadServer getCacheReadInterface(String readType) {
		if (redisMap.containsKey(readType)) {
			return (RedisReadServer) redisMap.get(readType);
		} else {
			throw  new RuntimeException("can not find rediscacheReadType : [" + readType + "]");
		}
	}

	public RedisWriteServer getCacheWriteInterface(String writeType) {
		if (redisMap.containsKey(writeType)) {
			return (RedisWriteServer) redisMap.get(writeType);
		} else {
			throw  new RuntimeException("can not find rediscacheWriteType : [" + writeType + "]");
		}
	}
	
	public void setRedisMap(Map<String, RedisReadServer> redisMap) {
		this.redisMap = redisMap;
	}

}
