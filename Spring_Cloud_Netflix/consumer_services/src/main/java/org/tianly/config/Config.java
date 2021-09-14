package org.tianly.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: Config
 * @Description:
 * @author: tianly
 * @date: 2021/8/27 23:04
 */
@Configuration
public class Config {

    @Bean
    @LoadBalanced
    public RestTemplate loadBalance(){
        return  new RestTemplate();
    }
}
