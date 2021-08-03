package cn.dszx.Handle;

import cn.dszx.Utils.JwtsUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class myHandle implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (!StringUtils.hasLength(token)){
            throw new RuntimeException();
        }
        Claims body = JwtsUtil.getBody(token);
        List<String> authen = (List<String>)body.get("authen");
        UserDetails userDetails = User.builder()
                .username(body.get("user"))
                .build();


    }
}
