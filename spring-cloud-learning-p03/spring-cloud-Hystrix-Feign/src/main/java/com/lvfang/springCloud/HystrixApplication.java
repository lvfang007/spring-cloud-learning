package com.lvfang.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @描述
 * @创建人 lvfang
 * @创建时间 2019/12/23
 */
@EnableHystrix
@EnableFeignClients
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class HystrixApplication {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run( HystrixApplication.class, args );
    }
}
