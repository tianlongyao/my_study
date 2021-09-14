package org.tianly.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: HelloController
 * @Description:
 * @author: tianly
 * @date: 2021/9/2 14:47
 */
@RestController
@RequestMapping("/v1")
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "message from spring cloud alibaba open-services";
    }
}
