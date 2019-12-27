package com.lvfang.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Author: LvFang
 * @Date: Created in 2019/12/24.
 * @Description:
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

    /**
     * cloud 配置中心
     *
     *  获取git信息地址：
     *      http://localhost:12000/config-client/dev
     *
     *  直接获取git上的文件内容
     *      http://localhost:12000/config-client-dev.yml
     *
     *
     *  Spring Cloud Config 也提供本地存储配置的方式。
     *      本地src/main/resource
     *          spring.profiles.active=native
     *      本地其他位置
     *          spring.cloud.config.server.native.searchLocations=file:E:/propertiesPath/
     *
     *
     *
     * 仓库中的配置文件会被转换成 Web 接口，访问可以参照以下的规则
             /{application}/{profile}[/{label}]
             /{application}-{profile}.yml
             /{label}/{application}-{profile}.yml
             /{application}-{profile}.properties
             /{label}/{application}-{profile}.properties
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
