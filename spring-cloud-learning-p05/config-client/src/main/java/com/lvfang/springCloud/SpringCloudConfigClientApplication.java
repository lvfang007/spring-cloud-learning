package com.lvfang.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: LvFang
 * @Date: Created in 2019/12/24.
 * @Description:
 */
@SpringBootApplication
public class SpringCloudConfigClientApplication {

    /**
     * 测试获取数据连接
     *      http://localhost:13000/info
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigClientApplication.class, args);
    }

}
