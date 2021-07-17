package org.tianly.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.tianly.redis.util.JsonCustomSerializer;
import org.tianly.redis.util.RedisStringTemplateCustomSerializer;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * @ClassName: RedisTemplateConfig
 * @Description: Redis配置
 * @author: tianly
 * @date: 2021/5/19 11:26
 */
@Configuration
@EnableCaching
public class RedisTemplateConfig {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.db}")
    private Integer db;


    private RedisConnectionFactory redisConnectionFactory;

    @PostConstruct
    public void iniConnectionFactory(){
        redisConnectionFactory = myRedisConnectionFactory();
    }


    private JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //池支持的最大连接数。 默认值 8。以前是1024
        jedisPoolConfig.setMaxTotal(50);
        //池中空闲连接的最大数目。 默认值 8
        jedisPoolConfig.setMaxIdle(50);
        //池中空闲连接的最小数目。默认值0。
        jedisPoolConfig.setMinIdle(0);
        //没有可用连接时客户端必须等待的最大毫秒数。以前 100000。官方推荐默认值 -1
        jedisPoolConfig.setMaxWaitMillis(-1);
        //指定在从池借用连接之前是否使用PING命令验证连接。将从池中删除无效连接。
        jedisPoolConfig.setTestOnBorrow(false);
        return jedisPoolConfig;
    }

    @Bean
    public RedisConnectionFactory myRedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setDatabase(db);
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();

        jpcb.poolConfig(jedisPoolConfig());
        //使用连接池
        jpcb.and().usePooling();
        //读超时时间
        jpcb.and().readTimeout(Duration.ofSeconds(60));
        jpcb.and().connectTimeout(Duration.ofSeconds(60));
        return new JedisConnectionFactory(redisStandaloneConfiguration, jpcb.build());
    }

    @Bean
    public RedisTemplate<Object, Object> redisGenericTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        redisTemplate.setDefaultSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<Object, Object> redisJDKTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        redisTemplate.setDefaultSerializer(jdkSerializer);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jdkSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jdkSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<Object, Object> redisJsonCustomTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        JsonCustomSerializer jsonCustomSerializer = new JsonCustomSerializer();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        redisTemplate.setDefaultSerializer(jsonCustomSerializer);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jsonCustomSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jsonCustomSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    //2021.5.20 新增StringRedisTemplate
    @Bean
    public RedisTemplate redisStringTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        StringRedisSerializer keySerializer = new StringRedisSerializer();
        RedisStringTemplateCustomSerializer valueSerializer = new RedisStringTemplateCustomSerializer();

        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setHashKeySerializer(keySerializer);
        redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setHashValueSerializer(valueSerializer);
        return  redisTemplate;
    }





    @Bean
    public CacheManager cacheManager() {
        JsonCustomSerializer serializer = new JsonCustomSerializer();
        RedisCacheConfiguration configuration  = RedisCacheConfiguration.defaultCacheConfig();
        configuration  =
                configuration .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));
        RedisCacheManager cacheManager =
                RedisCacheManager.builder(myRedisConnectionFactory()).cacheDefaults(configuration)
                .build();
        return cacheManager;
    }

}
