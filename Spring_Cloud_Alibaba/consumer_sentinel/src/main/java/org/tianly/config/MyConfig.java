package org.tianly.config;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.tianly.uitl.ExceptionUtil;

/**
 * @ClassName: Config @Description:
 *
 * @author: tianly
 * @date: 2021/9/2 14:50
 */
@Configuration
public class MyConfig {
  @Bean
  @LoadBalanced
  @SentinelRestTemplate(
      fallback = "fallback",
      fallbackClass = ExceptionUtil.class,
      blockHandler = "handleException",
      blockHandlerClass = ExceptionUtil.class)
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
