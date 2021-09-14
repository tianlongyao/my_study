package org.tianly.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: HelloController
 * @Description:
 * @author: tianly
 * @date: 2021/8/27 23:51
 */
@RestController
@RequestMapping("/v1")
public class HelloController {

    @GetMapping("/helloWorld")
    public  String helloWorld(){
        System.out.println("open-services.....");
        return  "HelloController_helloWorld";
    }
}
