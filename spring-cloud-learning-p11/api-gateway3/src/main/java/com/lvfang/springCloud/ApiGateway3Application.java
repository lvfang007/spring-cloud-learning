package com.lvfang.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @Author: LvFang
 * @Date: Created in 2019/12/24.
 * @Description: gateway-基于过滤器限流
 */
@SpringBootApplication
public class ApiGateway3Application {

    /**
     * 基于gateway的熔断实现
     *
     *
     *
     * 测试路径：http://localhost:14000/hello1/hello
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiGateway3Application.class, args);
    }


}
