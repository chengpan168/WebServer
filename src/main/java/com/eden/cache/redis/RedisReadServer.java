package com.eden.cache.redis;


import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisReadServer{
	
	public List<String> get(String key, String mapKey);
	public String get(String key);
	public <T> T get(String key,Class<T> clazz);
	public Set<String> getMapKeys(String key);
	public Set<String> getKeysByPattern(String patternKey);

	public Map<String, String> getMap(String key);

	public List<String> getMapVal(String key);
	
	public String getMapVal(String cacheKey,String mapKey);
	
	public boolean isExists(String key);

	public boolean isExists(byte[] byteKey);

	public Set<String> getSet(String key);
	
	public List<String> getList(String key);
	
	/**
	 * 堆栈(后进先出)
	 * @param key
	 * @return
	 */
	public String brpopDataQueue(String key);
	
	/**
	 * 出队(先进先出)
	 * @param key
	 * @return
	 */
	public String blpopDataQueue(String key);
	
	public long llenQueueSize(String key);
	
	
	
}
