package org.tianly.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "OPEN-SERVICE",fallbackFactory =
        HelloServicesFallbackFactory.class)
public interface HelloServices {
  @GetMapping("/v1/helloWorld")
  public String hello();
}
