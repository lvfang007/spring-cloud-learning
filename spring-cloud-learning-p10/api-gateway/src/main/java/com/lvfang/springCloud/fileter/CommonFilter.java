package com.lvfang.springCloud.fileter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @描述 全局过滤器
 * @创建人 lvfang
 * @创建时间 2019/12/25
 */
public class CommonFilter implements GlobalFilter, Ordered {

    private static final Log log = LogFactory.getLog(CommonFilter.class);

    /**
     * 全局过滤器执行实现GlobalFilter，复写filter方法即可，与自定义过滤器不同的是，它会将每个请求都去过滤，而自定义过滤器则需要配到对应的路由规则对指定的url使用
     */

    /**
     * 过滤逻辑书写
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");

        log.info(exchange.getRequest().getURI().getRawPath().toString() + "请求进入全局过滤器CommonFilter ");
        // 判断是否包含token
//        if (token == null || token.isEmpty()) {
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }

        return chain.filter(exchange);
    }

    /**
     * 设置优先级
     * @return
     */
    @Override
    public int getOrder() {
        return -100;
    }
}
