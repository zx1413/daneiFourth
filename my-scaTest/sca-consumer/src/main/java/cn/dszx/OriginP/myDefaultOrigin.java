package cn.dszx.OriginP;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
@Component
public class myDefaultOrigin implements RequestOriginParser {
//    设置拦截黑名单参数 mytest？ 获取参数拦截
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        String mytest = httpServletRequest.getParameter("mytest");
        return mytest;
    }
}
