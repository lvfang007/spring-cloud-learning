package com.lvfang.springCloud.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @描述
 * @创建人 lvfang
 * @创建时间 2019/12/23
 */
@Component
public class HelloRemoteHystrix implements HelloRemote {

    public String hello(@RequestParam(value = "name") String name) {
        return "Hello World! 服务出错了，HelloRemoteHystrix做了熔断降级处理。";
    }

}
