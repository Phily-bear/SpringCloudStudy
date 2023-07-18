package com.test.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class TestFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取ServerHttpRequest对象
        final ServerHttpRequest request = exchange.getRequest();
        //打印一下所有的请求参数
        System.out.println(request.getQueryParams());
        final List<String> value = request.getQueryParams().get("test");
        if (value!=null && value.contains("1")){
            //将ServerWebExchange向过滤链的下一级传递（跟JavaWeb中介绍的过滤器其实是差不多的）
            return chain.filter(exchange);
        }
        else {
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}