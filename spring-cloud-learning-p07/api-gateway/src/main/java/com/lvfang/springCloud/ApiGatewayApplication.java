package com.lvfang.springCloud;

import com.lvfang.springCloud.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

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
     *
     *
     *  zuul的4中过滤器：
             PRE：这种过滤器在请求被路由之前调用。我们可利用这种过滤器实现身份验证、在集群中选择请求的微服务、记录调试信息等。
             ROUTING：这种过滤器将请求路由到微服务。这种过滤器用于构建发送给微服务的请求，并使用 Apache HttpClient 或 Netfilx Ribbon 请求微服务。
             POST：这种过滤器在路由到微服务以后执行。这种过滤器可用来为响应添加标准的 HTTP Header、收集统计信息和指标、将响应从微服务发送给客户端等。
             ERROR：在其他阶段发生错误时执行该过滤器。 除了默认的过滤器类型，Zuul 还允许我们创建自定义的过滤器类型。例如，我们可以定制一种 STATIC 类型的过滤器，直接在 Zuul 中生成响应，而不将请求转发到后端的微服务。
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }
}
