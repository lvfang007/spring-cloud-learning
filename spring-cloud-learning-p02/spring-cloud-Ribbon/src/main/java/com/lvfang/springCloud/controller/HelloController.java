package com.lvfang.springCloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: LvFang
 * @Date: Created in 2019/12/23.
 * @Description:
 */
@RestController
@RequestMapping
public class HelloController {

    /**
     * 用于测试负载均衡
     */
    @Value("${server.port}")
    String port;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient client;

    /**
     * 测试路由
     * @param name
     * @return
     */
    @RequestMapping("/")
    public String hello(@RequestParam(value = "name",defaultValue = "lvfang") String name) {

        String result = restTemplate.getForObject("http://SERVICE-PRODUCER/hello?name="+name,String.class);

        return result;
    }
}
