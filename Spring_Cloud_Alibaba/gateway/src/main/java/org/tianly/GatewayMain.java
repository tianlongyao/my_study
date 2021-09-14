package org.tianly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @author: tianly
 * @date: 2021/9/7 22:52
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayMain {
  public static void main(String[] args) {
    new SpringApplicationBuilder()
        .sources(GatewayMain.class)
        .web(WebApplicationType.REACTIVE)
        .run(args);
  }

  //  @Bean
  //  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
  //    return builder.routes()
  //            .route("path_route", r -> r.path("/feign/{segment}")
  //                    .uri("http://localhost:18082"))
  //            .build();
  //  }
}
