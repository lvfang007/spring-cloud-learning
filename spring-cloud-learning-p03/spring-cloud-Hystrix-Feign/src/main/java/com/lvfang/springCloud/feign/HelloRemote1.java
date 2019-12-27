package com.lvfang.springCloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: LvFang
 * @Date: Created in 2019/12/23.
 * @Description:
 */
@FeignClient(name = "service-producer-test",fallback = HelloRemoteHystrix1.class)
public interface HelloRemote1 {

    @RequestMapping("/hello111111/")
    String hello(@RequestParam(value = "name") String name);
}
