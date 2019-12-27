package com.lvfang.springCloud.controller;

import com.lvfang.springCloud.feign.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LvFang
 * @Date: Created in 2019/12/23.
 * @Description:
 */
@RestController
@RequestMapping
public class HelloController {

    @Autowired
    HelloRemote helloRemote;

    /**
     * 测试路由
     * @param name
     * @return
     */
    @RequestMapping("/")
    public String hello(@RequestParam(value = "name",defaultValue = "lvfang") String name) {

        return helloRemote.hello(name + "!");
    }

    /**
     * 测试路由1
     * @param name
     * @return
     */
    @RequestMapping("/aa")
    public String hello01(@RequestParam(value = "name",defaultValue = "lvfang") String name) {

        return helloRemote.hello(name + "!");
    }
}
