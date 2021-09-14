package org.tianly.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.tianly.feign.HelloServices;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: HelloController @Description:
 *
 * @author: tianly
 * @date: 2021/9/2 14:45
 */
@RestController
@RequestMapping("/feign")
public class HelloFeignController {
  @Autowired private HelloServices helloServices;

  @GetMapping("/hello")
  public String hello() {
    return helloServices.hello();
  }

  public String helloBlockHandler(BlockException e) {
    return "HelloServices helloBlockHandler ";
  }

  public String helloFallback(Throwable e) {
    return "HelloServices helloFallback";
  }
}
