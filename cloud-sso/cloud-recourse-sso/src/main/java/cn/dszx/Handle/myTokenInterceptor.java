package cn.dszx.Handle;



import cn.dszx.Service.resourceSerivice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class myTokenInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger("myTokenInterceptor");

    private RestTemplate restTemplate;
    public myTokenInterceptor(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }


    private resourceSerivice remoteAuthService;
    public myTokenInterceptor(resourceSerivice remoteAuthService){
        this.remoteAuthService=remoteAuthService;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("authen");
        if (!StringUtils.hasLength(token)){
            throw new RuntimeException("this is error");
        }
//        System.out.println(token);
        String url="http://cloud-sso-authen/auth/getInfo?token="+token;
        Map<String,Object> map= restTemplate.getForObject(url,Map.class);
        Boolean expired=(Boolean)map.get("thTime");
        String username=(String)map.get("username");
//        String password = (String)map.get("password");
        List<String> list=(List<String>)map.get("authen");
//        Map<String, Object> map = remoteAuthService.getFeign(token);
//        Boolean expired=(Boolean)map.get("thTime");
//        String username=(String)map.get("username");
//        System.out.println(username);
////        String password = (String)map.get("password");
//        List<String> list=(List<String>)map.get("authen");
//        System.out.println(list);

//        if (!expired) {
//            throw new RuntimeException();
//        }
        String[] strings = list.toArray(new String[]{});
        logger.error(strings.toString());
        logger.trace(strings.toString());
        logger.debug(strings.toString());
        logger.info(strings.toString());
        logger.warn(strings.toString());
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
