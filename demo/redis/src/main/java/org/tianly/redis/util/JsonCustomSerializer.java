package org.tianly.redis.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @ClassName: JsonSerializer
 * @Description: 完全擦除类信息，仅保留JSON格式的数据
 * @author: tianly
 * @date: 2021/5/19 17:06
 */
public class JsonCustomSerializer<T> implements RedisSerializer<T>{
    private ObjectMapper objectMapper = new ObjectMapper();

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
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        } else {
            try {
                return (T) new String(bytes, "UTF-8");
            } catch (Exception var3) {
               return (T) jsonStringDeserialize(bytes);
            }
        }
    }


    private String jsonStringDeserialize(byte[] bytes) throws SerializationException{
        if (bytes == null || bytes.length == 0) {
            return null;
        } else {
            try {
                //反序列化时，当做String类型处理，具体类型在业务代码中进行处理
                return this.objectMapper.readValue(bytes, 0, bytes.length,String.class);
            } catch (Exception var3) {
                throw new SerializationException("Could not read JSON: " + var3.getMessage(), var3);
            }
        }
    }
}
