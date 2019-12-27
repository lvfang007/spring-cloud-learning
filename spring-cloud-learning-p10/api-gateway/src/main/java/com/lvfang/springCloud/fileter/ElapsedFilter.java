package com.lvfang.springCloud.fileter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @描述 自定义过滤器，统计各个请求的耗时
 * @创建人 lvfang
 * @创建时间 2019/12/25
 */
public class ElapsedFilter implements GatewayFilter,Ordered {

    private static final Log log = LogFactory.getLog(ElapsedFilter.class);
    private static final String ELAPSED_TIME_BEGIN = "elapsedTimeBegin";

    /**
     * 过滤逻辑
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {
        exchange.getAttributes().put(ELAPSED_TIME_BEGIN,System.nanoTime());


        /**
         * 这里需要说明的是，gateway的过滤器并不像zuul，它只有两种类型'pre'和'post'。
         * 我们发现实现GatewayFilter后只需实现一个filter方法，那么我们怎么区分'pre'和'post'。
         *
         * pre：chain.filter(exchange) 之前
         * post：chain.filter(exchange)之后的也就是 then 里边的
         *
         * 总结一句就是chain.filter(exchange) 之前的就是 “pre” 部分，之后的也就是 then 里边的是 “post” 部分。
         */
        return chain.filter(exchange).then(
                Mono.fromRunnable(() ->{
                    Long startTime = exchange.getAttribute(ELAPSED_TIME_BEGIN);
                    if(startTime != null) {
                        //打印请求耗时
                        log.info(exchange.getRequest().getURI().getRawPath() + ": " + (System.nanoTime()-startTime) + "ns");
                    }
                })
        );
    }

    /**
     * 优先级 越小越高
     * @return
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
