package com.lvfang.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Author: LvFang
 * @Date: Created in 2019/12/24.
 * @Description:
 */
@EnableHystrixDashboard
@SpringBootApplication
public class HystrixDashboardApplication {

    /**
     * Hystrix-Dashboard是用来做熔断监控使用的，需要监控的服务需配置hystrix.stream。具体查看spring-cloud-Hystrix-Feign项目
     *
     * 进入监控地址：
     *      http://localhost:11000/hystrix
     *
     *  进入监控页面需要填写三个参数
     *      url：http://hostname:port/actuator/hystrix.stream
     *      timeout：监控频率
     *      title：名称
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication.class, args);
    }

}
