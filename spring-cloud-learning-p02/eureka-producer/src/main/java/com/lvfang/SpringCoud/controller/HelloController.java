package com.lvfang.SpringCoud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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

    /**
     * 测试路由
     * @param name
     * @return
     */
    @RequestMapping("/hello")
    public String hello(@RequestParam String name) {
        return "Hello, " + name + " " + new Date();
    }
}
