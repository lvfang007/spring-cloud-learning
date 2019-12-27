package com.lvfang.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: LvFang
 * @Date: Created in 2019/12/23.
 * @Description:
 */
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaConsumer {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     *
     * @EnableEurekaClien 注解开启之后，服务启动后,服务就查以注册了
     * @EnableDiscoveryClient
     *
     * 另外，这里延伸一下，@EnableDiscoveryClient与@EnableEurekaClient区别
     *
     *     注解@EnableEurekaClient上有@EnableDiscoveryClient注解，可以说基本就是EnableEurekaClient有@EnableDiscoveryClient的功
     * 能，另外上面的注释中提到，其实@EnableEurekaClientz注解就是一种方便使用eureka的注解而已，可以说使用其他的注册中心后，都可以使
     * 用@EnableDiscoveryClient注解，但是使用@EnableEurekaClient的情景，就是在服务采用eureka作为注册中心的时候，使用场景较为单一。
     *
     * @EnableDiscoveryClient和@EnableEurekaClient
     *
     *
     * 获取zipkinjar包，搭建zipkin服务端，并配置zipkin地址到要监控的服务
     *  curl -sSL https://zipkin.io/quickstart.sh | bash -s
        java -jar zipkin.jar
     */
    public static void main(String[] args) {
        SpringApplication.run( EurekaConsumer.class, args );
    }
}
