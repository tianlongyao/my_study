package org.tianly.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: HelloRestController @Description:
 *
 * @author: tianly
 * @date: 2021/9/6 22:31
 */
@RestController
@RequestMapping("/rest")
public class HelloRestController {
  @Autowired private RestTemplate loadBalance;

  @GetMapping("/hello")
  @SentinelResource(fallback = "fallback")
  public String hello() {
    String result =
        loadBalance.getForObject(
            "http://spring-cloud-alibaba-open-services/v1/hello", String.class);
    return result;
  }

  public String fallback() {
    return "SentinelResource fallback";
  }
}
