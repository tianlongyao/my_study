package org.tianly.feign;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName: HelloServicesFallbackFactory
 * @Description:
 * @author: tianly
 * @date: 2021/9/3 15:37
 */
@Component
public class HelloServicesFallbackFactory implements FallbackFactory<HelloServices> {

    @Override
    public HelloServices create(Throwable cause) {
        return new HelloServices() {
            @Override
            public String hello() {
                return "HelloServicesFallbackFactory#hello()";
            }
        };
    }
}
