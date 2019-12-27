package com.lvfang.springCloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: LvFang
 * @Date: Created in 2019/12/24.
 * @Description:
 */
public class TokenFilter extends ZuulFilter {

    /**
     * 过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。
     * 这里定义为pre，代表会在请求被路由之前执行。
     *
     * @return
     */
    public String filterType() {
        String type = "pre";
        System.out.println("1、 方法filterType进行过滤器级别设置为：" + type);
        return "pre";
    }

    /**
     * filter执行顺序，通过数字指定。
     * 数字越大，优先级越低。
     * @return
     */
    public int filterOrder() {
        System.out.println("2、方法filterOrder进行过滤器优先级设置为：" + 0);
        return 0;
    }

    /**
     * 判断该过滤器是否需要被执行。这里我们直接返回了true，因此该过滤器对所有请求都会生效。
     * 实际运用中我们可以利用该函数来指定过滤器的有效范围。
     * @return
     */
    public boolean shouldFilter() {
        System.out.println("3、方法shouldFilter进行判断该过滤器是否被执行");
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * @return
     */
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        //设置自己的过滤逻辑
        System.out.println("4、方法run过滤器的具体逻辑编写");

        /**
         * 请求中不带token参数的拦截
         */
        String token = request.getParameter("token");
        if (token == null || token.isEmpty()) {
            System.out.println("被zuul网关的 TokenFilter 过滤");
            ctx.setSendZuulResponse(false);//令 Zuul 过滤该请求
            ctx.setResponseStatusCode(401);//返回对应处理码
            ctx.setResponseBody("token is empty");
        }
        return null;
    }
}
