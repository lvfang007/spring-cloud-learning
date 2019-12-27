package com.lvfang.springCloud;

import com.lvfang.springCloud.fileter.CommonFilter;
import com.lvfang.springCloud.fileter.ElapsedFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @Author: LvFang
 * @Date: Created in 2019/12/24.
 * @Description:
 */
@SpringBootApplication
public class ApiGatewayApplication {

    /**
     * 即：SERVICE-CONSUMER 和 SERVICE-PRODUCER
     *
     * 原服务访问PRODUCER：
     *      http://localhost:8001/
     *      http://localhost:8002/
     * 通过gateway网关访问:(默认路由规则)
     *      http://localhost:14000/SERVICE-PRODUCER/hello
     *      http://localhost:14000/SERVICE-CONSUMER/
     *
     *
     *
     * 测试路径：http://localhost:14000/hello1/hello
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    /**
     * 注入全局过滤器
     * @return
     */
    @Bean
    public CommonFilter commonFilter(){
        return new CommonFilter();
    }
    /**
     * producer 服务路由
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator producerRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/hello1/**")
                        .filters(f -> f.stripPrefix(1)
                                .filter(new ElapsedFilter())
                                .addResponseHeader("X-Response-Default-name", "Default-lvfang"))
                        .uri("lb://SERVICE-PRODUCER")
                        .order(0)
                        .id("producer_service")
                )
                .build();
    }
}
