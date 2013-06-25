package com.eden.cache.redis;


import javax.annotation.Resource;



public class BaseRedisServer {
	
	@Resource
	private  RedisServerFactory reidsServerFactory;
	
	public  RedisReadServer getRedisCacheReadServer(String redisReadType) {
		return reidsServerFactory.getCacheReadInterface(redisReadType);
	}
	
	public  RedisWriteServer getRedisCacheWriteServer(String redisWriteType) {
		return reidsServerFactory.getCacheWriteInterface(redisWriteType);
	}
	
	
}
