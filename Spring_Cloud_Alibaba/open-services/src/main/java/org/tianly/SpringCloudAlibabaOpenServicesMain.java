package org.tianly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: SpringCloudAlibabaOpenServicesMain
 * @Description:
 * @author: tianly
 * @date: 2021/9/2 14:16
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudAlibabaOpenServicesMain {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaOpenServicesMain.class,args);
    }
}
