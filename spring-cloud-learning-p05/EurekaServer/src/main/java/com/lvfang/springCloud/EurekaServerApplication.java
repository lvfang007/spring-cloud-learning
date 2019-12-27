package com.lvfang.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: LvFang
 * @Date: Created in 2019/12/23.
 * @Description: 文档地址： http://cloud.spring.io/spring-cloud-static/Finchley.RELEASE/single/spring-cloud.html
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    /**
     * @EnableEurekaServer注解说明此服务是一个Eureka注册中心服务，扮演注册中心的角色
     *
     * * 另外，这里延伸一下，@EnableDiscoveryClient与@EnableEurekaClient区别
     *
     *     注解@EnableEurekaClient上有@EnableDiscoveryClient注解，可以说基本就是EnableEurekaClient有@EnableDiscoveryClient的功
     * 能，另外上面的注释中提到，其实@EnableEurekaClientz注解就是一种方便使用eureka的注解而已，可以说使用其他的注册中心后，都可以使
     * 用@EnableDiscoveryClient注解，但是使用@EnableEurekaClient的情景，就是在服务采用eureka作为注册中心的时候，使用场景较为单一。
     *
     *
     * 共同点就是：
     *          都是能够让注册中心能够发现，扫描到该服务。
     * 不同点：
     *  @EnableEurekaClient只适用于Eureka作为注册中心，@EnableDiscoveryClient 可以是其他注册中心。
     *  @EnableDiscoveryClient基于spring-cloud-commons；而 @EnableEurekaClient基于spring-cloud-netflix
     */
    public static void main(String[] args) {
        SpringApplication.run( EurekaServerApplication.class, args );
    }
}
