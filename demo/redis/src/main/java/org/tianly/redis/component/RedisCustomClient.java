package org.tianly.redis.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @ClassName: RedisCustomerTemplateClient
 * @Description: 新版本Redis客户端
 * @author: tianly
 * @date: 2021/5/24 14:55
 */
@Component
@Slf4j
public class RedisCustomClient {
	@Autowired
	private RedisTemplate redisStringTemplate;

	private ObjectMapper objectMapper = new ObjectMapper();

	@PostConstruct
	public void init() {
		this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Deprecated
	public RedisTemplate getRedisStringTemplate() {
		return redisStringTemplate;
	}

	private JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}


	/**
	 *
	 * @param key
	 * @param value
	 */
	public void setValue(Object key,Object value)  {
		if(null == key){
			return;
		}
		redisStringTemplate.opsForValue().set(key,value);
	}

	/**
	 * key-value 设置过期时间
	 * @param key  键
	 * @param value 值
	 * @param expire 过期时间的数字
	 * @param timeUnit 时间单位
	 */
	public void setValueExpire(String key,Object value,long expire,TimeUnit timeUnit) {
		if(StringUtils.isEmpty(key)){
			return;
		}
		redisStringTemplate.opsForValue().set(key,value,expire,timeUnit);
	}

	public  <T>T  getAndSetValue(Object key,Object value,Class<T> clazz){
		if(null == key){
			return null;
		}else if(null == clazz){
			throw new RuntimeException("clazz can not be null");
		} else {
			String jsonStr = (String) redisStringTemplate.opsForValue().getAndSet(key,value);
			if(String.class == clazz){
				return (T) jsonStr;
			}
			try {
				return objectMapper.readValue(jsonStr,clazz);
			} catch (JsonProcessingException e) {
				throw new RuntimeException("writeAsObject error,key="+key+";jsonStr ="+jsonStr);
			}
		}
	}

	/**
	 * key-value 获取
	 * @param key 键
	 * @param clazz 需要转化的类型
	 * @param <T>  返回类型
	 * @return
	 */
	public  <T> T getValue(Object key,Class<T> clazz) {
		if(null == key){
			return null;
		}else if(null == clazz){
			throw new RuntimeException("clazz can not be null");
		} else {
			String jsonStr = (String) redisStringTemplate.opsForValue().get(key);
			if(String.class == clazz){
				return (T) jsonStr;
			}
			try {
				return objectMapper.readValue(jsonStr,clazz);
			} catch (JsonProcessingException e) {
				throw new RuntimeException("writeAsObject error,key="+key+";jsonStr ="+jsonStr);
			}
		}
	}

	public  <T> T getValue(Object key,Class<?> collectionClass, Class<?>... elementClasses) {
		if(null == key){
			return null;
		}else if(null == collectionClass || null == elementClasses){
			throw new RuntimeException("collectionClass or elementClasses can not be null!");
		}
		else {
			String jsonStr = (String) redisStringTemplate.opsForValue().get(key);
			try {
				return objectMapper.readValue(jsonStr,getCollectionType(collectionClass,elementClasses));
			} catch (JsonProcessingException e) {
				throw new RuntimeException("writeAsObject error,key="+key+";jsonStr ="+jsonStr);
			}
		}
	}

	/**
	 * key -value 增加
	 * @param key
	 * @param increment
	 * @return
	 */
	public long valueIncrement(String key,long increment){
		if(StringUtils.isEmpty(key) ){
			throw new RuntimeException("opsForValue().increment();key can not be null");
		}
		return  redisStringTemplate.opsForValue().increment(key,increment);
	}

	/**
	 *  key -value 增加
	 * @param key
	 * @param increment
	 * @return
	 */
	public double valueIncrement(String key,double increment){
		if(StringUtils.isEmpty(key) ){
			throw new RuntimeException("opsForValue().increment();key can not be null");
		}
		return  redisStringTemplate.opsForValue().increment(key,increment);
	}

	/**
	 * redis hash存
	 * @param key
	 * @param item
	 * @param value
	 */
	public void setHashValue(String key,String item,Object value) {
		if(StringUtils.isEmpty(key) || StringUtils.isEmpty(item)){
			return;
		}
		redisStringTemplate.opsForHash().put(key,item,value);

	}

	/**
	 *  redis hash取
	 * @param key
	 * @param item
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public <T>T getHashValue(String key,String item,Class<T> clazz)  {
		if(StringUtils.isEmpty(key) || StringUtils.isEmpty(item)){
			return null;
		}else {
			String jsonStr = (String) redisStringTemplate.opsForHash().get(key,item);
			if(String.class == clazz){
				return (T) jsonStr;
			}
			if(StringUtils.isEmpty(key) || StringUtils.isEmpty(item)){
				return null;
			}else {
				try {
					return objectMapper.readValue(jsonStr,clazz);
				} catch (JsonProcessingException e) {
					throw new RuntimeException("writeAsObject error,key="+key+";item="+item+";jsonStr ="+jsonStr);
				}
			}
		}
	}

	/**
	 * set取
	 * @param key
	 * @return
	 */
	public Set getSetValue(String key)  {
		if(StringUtils.isEmpty(key)){
			return null;
		}else {
			return redisStringTemplate.opsForSet().members(key);
		}
	}

	/**
	 *  set存
	 * @param key
	 * @param value
	 */
	public void setSetValue(String key,Object value){
		if(StringUtils.isEmpty(key) ){
			return;
		}
		redisStringTemplate.opsForSet().add(key,value);
	}

	/**
	 * Redis list 操作
	 * @param key
	 * @param value
	 */
	public void listRightPush(String key,Object value) {
		if(StringUtils.isEmpty(key) ){
			return;
		}
		redisStringTemplate.opsForList().rightPush(key,value);
	}

	public List listRange(Object key, long indexBegin, long indexEnd){
		return  listRange(key,indexBegin,indexEnd,null);
	}

	public <T> List<T> listRange(Object key, long indexBegin, long indexEnd,Class<T> clazz){
		if(String.class == clazz || null == clazz){
			return redisStringTemplate.opsForList().range(key,indexBegin,indexEnd);
		}else {
			List<String> list =redisStringTemplate.opsForList().range(key,indexBegin,indexEnd);
			List<T> ret =list.parallelStream().filter(Objects::nonNull).map(x-> {
				try {
					return objectMapper.readValue(x,clazz);
				} catch (JsonProcessingException e) {
					return null;
				}
			}).filter(Objects::nonNull).collect(Collectors.toList());
			return ret;
		}
	}


	/**
	 * 删除key
	 * @param key
	 */
	public void deleteKey(String key){
		redisStringTemplate.delete(key);
	}


	/**
	 * 批量删除keys
	 * @param keys
	 */
	public void deleteKey(Collection keys){
		redisStringTemplate.delete(keys);
	}
	/**
	 * 删除key
	 * @param key
	 */
	public boolean  hasKey(String key){
		return redisStringTemplate.hasKey(key);
	}

	/**
	 * 锁过期
	 * @param key
	 */
	public void expire(Object key,long timeOut,TimeUnit timeUnit){
		redisStringTemplate.expire(key,timeOut,timeUnit);
	}

	public Set keys(String keyPattern){
		return  redisStringTemplate.keys(keyPattern);
	}

	public Boolean setIfAbsent(Object k,Object v){
		return  redisStringTemplate.opsForValue().setIfAbsent(k,v);
	}

	public Boolean setIfAbsent(Object k,Object v,long l ,TimeUnit timeUnit){
		return  redisStringTemplate.opsForValue().setIfAbsent(k,v,l,timeUnit);
	}


	public List multiGet(Collection keys){
		return redisStringTemplate.opsForValue().multiGet(keys) ;
	}

}
