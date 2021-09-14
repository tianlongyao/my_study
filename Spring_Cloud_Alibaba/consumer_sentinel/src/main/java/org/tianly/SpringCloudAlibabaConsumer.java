package org.tianly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName: SpringCloudAlibabaConsumer @Description:
 *
 * @author: tianly
 * @date: 2021/9/2 14:32
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SpringCloudAlibabaConsumer {
  public static void main(String[] args) {
    SpringApplication.run(SpringCloudAlibabaConsumer.class, args);
  }
}
