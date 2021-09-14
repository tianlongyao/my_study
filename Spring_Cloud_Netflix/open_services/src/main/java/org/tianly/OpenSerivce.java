package org.tianly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: App
 * @Description:
 * @author: tianly
 * @date: 2021/8/26 21:01
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OpenSerivce {
    public static void main(String[] args) {
        SpringApplication.run(OpenSerivce.class,args);
    }
}
