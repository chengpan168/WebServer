package com.eden.cache.redis;


import java.util.List;
import java.util.Map;


public interface RedisWriteServer {

	public void save(String key, String value);

	public void save(String key, String value, int expireTime);

	public void save(String key, Object obj);

	public void save(String key, Object obj, int expireTime);

	public void replace(String key, Map<String, String> map, int expireTime);

	public void save(String key, Map<String, String> map);

	public void save(String key, Map<String, String> map, int expireTime);
	
	public void saveMapVal(String cacheKey,String mapKey,String mapVal);

	public void del(String key);
	
	public void del(String... keys);

	public void del(String key, String mapKey);
	
	public void saveSet(String key, String str, int expireTime); 

	public void delFromSet(String key, String setValue);
	
	public void saveList(String key,List<String> list,int expireTime);
	
	public void saveList(String key,List<String> list);
	
	/**
	 * 元素追加到队列头部
	 * @param key
	 * @param data
	 */
	public void lpushQueue(String key,String data);
	
	/**
	 * 队列后追加元素(入队)
	 * @param key
	 * @param data
	 */
	public void rpushQueue(String key,String data);

}
