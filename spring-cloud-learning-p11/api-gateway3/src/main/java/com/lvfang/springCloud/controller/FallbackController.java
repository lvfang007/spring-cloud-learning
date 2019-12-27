package com.lvfang.springCloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @描述
 * @创建人 lvfang
 * @创建时间 2019/12/26
 */
@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public String fallback() {
        return "Hello World!\nfrom gateway";
    }
}
