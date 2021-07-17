package org.tianly.redis.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @ClassName: StringCustomSerializer
 * @Description: 自定义的反序列化器
 * @author: tianly
 * @date: 2021/5/19 17:06
 */
public class RedisStringTemplateCustomSerializer implements RedisSerializer{
    private ObjectMapper objectMapper = new ObjectMapper();

	public RedisStringTemplateCustomSerializer() {
		this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Override
    public byte[] serialize(Object o) throws SerializationException {
        if(null == o){
            return new byte[0];
        }else {
            try {
                return  objectMapper.writeValueAsBytes(o);
            } catch (JsonProcessingException e) {
                throw new SerializationException("Could not write JSON: " + e.getMessage(), e);
            }
        }
    }

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		if (bytes == null || bytes.length == 0) {
			return null;
		} else {
			return new String(bytes);
		}
	}
}
