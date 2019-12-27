package com.lvfang.springCloud;

import com.lvfang.springCloud.filter.RateLimitByCpuGatewayFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.Duration;


/**
 * @Author: LvFang
 * @Date: Created in 2019/12/24.
 * @Description: gateway-基于过滤器限流
 */
@SpringBootApplication
public class ApiGateway2Application {

    /**
     * 即：SERVICE-CONSUMER 和 SERVICE-PRODUCER
     *
     * 限流方式：
     *    1、基于过滤器限流（本项目策略即是-采取令牌桶）
     *    2、基于过滤器工厂限流（gateway内置的RequestRateLimiterGatewayFilterFactory，其实现依赖于redis）
     *    3、基于系统负载的动态限流
     *
     * 限流策略：
         1、对请求的目标 URL 进行限流（例如：某个 URL 每分钟只允许调用多少次）
         2、对客户端的访问 IP 进行限流（例如：某个 IP 每分钟只允许请求多少次）
         3、对某些特定用户或者用户组进行限流（例如：非 VIP 用户限制每分钟只允许调用 100 次某个 API 等）
         4、多维度混合的限流。此时，就需要实现一些限流规则的编排机制（与、或、非等关系）
     *
     *
     *
     * 测试路径：http://localhost:14000/hello1/hello
     *
     * springboot 监控地址
     *
     *  http://localhost:14000/actuator
     *  http://localhost:14000/actuator/metrics
     *  http://localhost:14000/actuator/metrics/system.cpu.usage
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiGateway2Application.class, args);
    }


    @Autowired
    private RateLimitByCpuGatewayFilter rateLimitByCpuGatewayFilter;
    /**
     * producer 服务路由
     * @param builder
     * @return
     *
     * 测试路径：http://localhost:14000/hello1/hello
     */
    @Bean
    public RouteLocator producerRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/hello1/**")
                        .filters(f -> f.stripPrefix(1)
                                //添加IP限流过滤：指定了 bucket 的容量为 2 且每一秒会补充 1 个 Token
                                .filter(rateLimitByCpuGatewayFilter)
                                .addResponseHeader("X-Response-Default-name", "Default-lvfang"))
                        .uri("lb://SERVICE-PRODUCER")
                        .order(0)
                        .id("producer_service")
                )
                .build();
    }

}
