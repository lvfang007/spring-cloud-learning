package com.lvfang.springCloud.com.lvfang.springCloud;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: LvFang
 * @Date: Created in 2019/12/26.
 * @Description:
 */
public class RemoteAddrKeyResolver implements KeyResolver {

    public static final String BEAN_NAME = "remoteAddrKeyResolver";

    public Mono<String> resolve(ServerWebExchange exchange) {
        String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        System.out.println("IP: " + ip + " 进入 RemoteAddrKeyResolver ... ...");
        return Mono.just(ip);
    }

}
