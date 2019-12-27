package com.lvfang.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author: LvFang
 * @Date: Created in 2019/12/24.
 * @Description:
 */
@EnableZuulProxy
@SpringBootApplication
public class ApiGatewayApplication {

    /**
     *
     * Spring Cloud Zuul 在整合了 Eureka 之后，具备默认的服务路由功能，
     *      即：当我们这里构建的 api-gateway 应用启动并注册到 Eureka 之后，
     *          服务网关会发现上面我们启动的两个服务 producer 和 consumer，
     *          这时候 Zuul 就会创建两个路由规则。以服务id为准
     *
     *          即：SERVICE-CONSUMER 和 SERVICE-PRODUCER
     *
     * 原服务访问PRODUCER：
     *      http://localhost:8001/
     *      http://localhost:8002/
     * 通过zuul网关访问：（前提是zuul没配服务别名）
     *      http://localhost:14000/service-producer/hello
     *      http://localhost:14000/service-consumer
     * 通过zuul网关别名访问：
     *      http://localhost:14000/api-p/hello
     *      http://localhost:14000/api-c/
     * service-consumer
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
