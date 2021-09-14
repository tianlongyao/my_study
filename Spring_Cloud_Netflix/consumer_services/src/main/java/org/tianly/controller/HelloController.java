package org.tianly.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.tianly.feign.HelloServices;

/**
 * @ClassName: HelloController @Description:
 *
 * @author: tianly
 * @date: 2021/8/27 23:52
 */
@RestController
@RequestMapping("/v1")
public class HelloController {
  @Autowired private RestTemplate loadBalance;

  @GetMapping("/helloWorld")
  public String helloWorld() {
    String result =
        loadBalance.getForEntity("http://OPEN-SERVICE/v1/helloWorld", String.class).getBody();
    String ret = "consumer-services receive:" + result;
    return ret;
  }

  @Autowired private HelloServices services;

  @GetMapping("/hello")
  @HystrixCommand(
      fallbackMethod = "breakFallback",
      commandProperties = {
        // 开启断路器
        @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
        // 请求次数
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
        // 时间窗口期
        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
        // 失败率
        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
      })
  public String hello() {
    return "feign clients receive :" + services.hello();
  }
}
