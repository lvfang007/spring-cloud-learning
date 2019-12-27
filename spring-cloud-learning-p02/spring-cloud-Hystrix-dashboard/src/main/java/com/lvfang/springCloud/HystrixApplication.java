package com.lvfang.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @描述
 * @创建人 lvfang
 * @创建时间 2019/12/23
 */
@EnableHystrix
@EnableFeignClients
@EnableHystrixDashboard
@SpringBootApplication
public class HystrixApplication {

    /**
     * 监控地址：http://localhost:8006/actuator/hystrix.stream
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run( HystrixApplication.class, args );
    }
}
