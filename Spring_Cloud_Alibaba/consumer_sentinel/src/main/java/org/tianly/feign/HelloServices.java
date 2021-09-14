package org.tianly.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "spring-cloud-alibaba-open-services",fallbackFactory =
        HelloServicesFallbackFactory.class)
public interface HelloServices {
  @GetMapping("/v1/hello")
  public String hello();
}
