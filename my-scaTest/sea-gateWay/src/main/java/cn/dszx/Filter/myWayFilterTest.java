package cn.dszx.Filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
//模拟网关
@Component
public class myWayFilterTest implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
//        获取请求参数
        String name = request.getQueryParams().getFirst("name");
        if (!name.equals("dszx")){
            //        拦截
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.setComplete();
            throw new RuntimeException();
        }
//        让传递链继续传递
        return chain.filter(exchange);
    }
//    设置优先级
    @Override
    public int getOrder() {
//        数值越小优先级越靠前
        return -200;
    }
}
