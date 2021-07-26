package cn.dszx.Handle;


import io.jsonwebtoken.Claims;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import sso.util.myJwtsUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class myTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("authen");
        if (!StringUtils.hasLength(token)){
            throw new RuntimeException("this is error");
        }
        System.out.println(token);
        Claims claims = myJwtsUtil.getBody(token);
        List<String> list = (List<String>)claims.get("authen");
        String username =(String) claims.get("username");
//        构建userDetailU对象 用户名与密码必须要写 否则会报Cannot pass null or empty values to constructor 构造异常
        UserDetails user = User.builder()
                .username(username)
                .password("")
                .authorities(list.toArray(new String[]{}))
                .build();
//        将对象进行注册
        PreAuthenticatedAuthenticationToken authenToken = new PreAuthenticatedAuthenticationToken(user, user.getUsername(), user.getAuthorities());
//        与当前请求对象进行绑定
        authenToken.setDetails(new WebAuthenticationDetails(request));
//        绑定后存储在本地的上下文
        SecurityContextHolder.getContext().setAuthentication(authenToken);
        return true;
    }
}
