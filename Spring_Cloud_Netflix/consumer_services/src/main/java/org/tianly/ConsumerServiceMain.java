package org.tianly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName: ConsumerServiceMain
 * @Description:
 * @author: tianly
 * @date: 2021/8/27 23:14
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
public class ConsumerServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerServiceMain.class,args);
    }
}
