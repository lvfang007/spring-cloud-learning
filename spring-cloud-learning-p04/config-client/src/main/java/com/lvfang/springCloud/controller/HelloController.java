package com.lvfang.springCloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Author: LvFang
 * @Date: Created in 2019/12/24.
 * @Description:
 */
@RefreshScope
@RestController
public class HelloController {

    /**
     *
     *  注解：@RefreshScope实现动态刷新
     * 刷新地址：
     *      http://localhost:13000/actuator/refresh
     */
    @Value("${info.profile:error}")
    private String profile;

    @RequestMapping("/info")
    public Mono<String> hello() {
        return Mono.justOrEmpty(profile);
    }
}
