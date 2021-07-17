package org.tianly.redis.component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tianly.redis.pojo.Animal;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RedisCustomClientTest {
    @Autowired
    private  RedisCustomClient redisCustomClient;
    @Autowired
    private RedisTemplate redisGenericTemplate;

    @Test
    public  void testPut(){
        String prefix = "test::";
        String prefix_org = "org::";
        for (int i = 0; i < 10; i++) {
            if(i % 2 == 0 ){
                Animal animal = new Animal();
                animal.setName("test_"+i);
                redisCustomClient.setValue(prefix+"double::"+i,animal);
                redisGenericTemplate.opsForValue().set(prefix_org+"double::"+i,animal);
            }else {
                Animal animal = new Animal();
                animal.setName("test_"+i);
                redisCustomClient.setValue(prefix+"single::"+i,animal);
                redisGenericTemplate.opsForValue().set(prefix_org+"single::"+i,animal);
            }

        }

    }
}