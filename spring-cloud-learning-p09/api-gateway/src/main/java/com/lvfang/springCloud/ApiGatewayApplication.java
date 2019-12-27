package com.lvfang.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
