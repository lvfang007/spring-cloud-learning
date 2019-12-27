package com.lvfang.springCloud.fileter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import lombok.*;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: LvFang
 * @Date: Created in 2019/12/26.
 * @Description: 限流：令牌桶算法
 */
@Data
@Builder
@ToString
@CommonsLog
@AllArgsConstructor
@NoArgsConstructor
public class RateLimitByIpGatewayFilter implements GatewayFilter,Ordered {

    /**
     * 常见限流算法：
     *      （1）漏桶算法（Leaky Bucket）：
     *                  水（请求）先进入到漏桶里，漏桶以一定的速度出水（接口有响应速率），当水流入速度过大会直接溢出（访问频率超过接口响应速率），
     *              然后就拒绝请求，可以看出漏桶算法能强行限制数据的传输速率。
     *                  这里有两个变量起决定影响。即支持流量突发增多时可以存多少的水（burst），另一个是水桶漏洞的大小（rate）。
     *                  【缺点】因为漏桶的漏出速率是固定的参数，所以，即使网络中不存在资源冲突（没有发生拥塞），漏桶算法也不能使流突发（burst）到端口速率。
     *              因此，漏桶算法对于存在突发特性的流量来说缺乏效率。
     *
     *      （2）令牌桶算法（Token Bucket）
     *                  令牌桶算法（Token Bucket）和 Leaky Bucket 效果一样但方向相反的算法，更加容易理解。随着时间流逝，系统会按恒定 1/QPS 时间
     *              间隔（如果 QPS=100，则间隔是 10ms）往桶里加入 Token（想象和漏洞漏水相反，有个水龙头在不断的加水），如果桶已经满了就不再加了。
     *              新请求来临时，会各自拿走一个 Token，如果没有 Token 可拿了就阻塞或者拒绝服务。
     *                  【优点】令牌桶的另外一个好处是可以方便的改变速度。一旦需要提高速率，则按需提高放入桶中的令牌的速率。一般会定时（比如 100 毫秒）往桶中
     *              增加一定数量的令牌，有些变种算法则实时的计算应该增加的令牌的数量。
     *
     */

    int capacity;//桶的最大容量，即能装载 Token 的最大数量

    int refillTokens;//每次 Token 补充量

    Duration refillDuration;//补充 Token 的时间间隔

    private static final Map<String,Bucket> CACHE = new ConcurrentHashMap();

    private Bucket createNewBucket() {
        Refill refill = Refill.of(refillTokens,refillDuration);
        Bandwidth limit = Bandwidth.classic(capacity,refill);
        return Bucket4j.builder().addLimit(limit).build();
    }


    /**
     * 限流过滤逻辑：使用了 IP 来进行限制，当达到最大流量就返回 429 错误
     * @param exchange
     * @param chain
     * @return
     */
    public Mono<Void> filter(ServerWebExchange exchange,GatewayFilterChain chain) {
        // if (!enableRateLimit){
        //     return chain.filter(exchange);
        // }
        String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        Bucket bucket = CACHE.computeIfAbsent(ip,k -> createNewBucket());

        log.info("IP: " + ip + ", 请求允许 TokenBucket Available Tokens: " + bucket.getAvailableTokens());
        if (bucket.tryConsume(1)) {
            return chain.filter(exchange);
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            log.info("IP: " + ip + ", 请求超出限制，请稍后操作... ...");
            return exchange.getResponse().setComplete();
        }
    }


    /**
     * 优先级设置
     * @return
     */
    public int getOrder() {
        return -1000;
    }
}
