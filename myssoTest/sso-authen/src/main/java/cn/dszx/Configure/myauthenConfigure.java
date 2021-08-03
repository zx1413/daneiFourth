package cn.dszx.Configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import cn.dszx.util.myJwtsUtil;
import cn.dszx.util.webJsonUtil;


import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class myauthenConfigure extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder getBcry(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        登录界面设置
        http.formLogin().loginProcessingUrl("/login").successHandler(getSuccess()).failureHandler(getFail());
//        错误界面设置 例如 accessDeniedHandler 访问禁止(没有限权)  authenticationEntryPoint 访问禁止(没有认证)
        http.exceptionHandling().authenticationEntryPoint(getPoint());
        http.authorizeRequests().antMatchers("/login").permitAll();
    }
//    登录成功后显示的数据
    public AuthenticationSuccessHandler getSuccess(){
           return (req,resp,auth) -> {
               HashMap<String, Object> map = new HashMap<>();
               map.put("status",HttpServletResponse.SC_OK);
               map.put("msg","login ok");
//               将用户名与用户限权进行包装
               Map<String,Object> mymap = new HashMap<>();
               List<String> list = new ArrayList();
               auth.getAuthorities().forEach(x-> list.add(x.getAuthority()));
               mymap.put("authen",list);
               User user = (User) auth.getPrincipal();
               mymap.put("username",user.getUsername());
               String jwts = myJwtsUtil.getJwts(mymap);
//               发送jwts令牌  也就是访问认证
               map.put("authen",jwts);

               webJsonUtil.senResponse(resp,map);

           };
    }
//    认证登录失败后显示的数据
    public AuthenticationFailureHandler getFail(){
         return (req,resp,exception) -> {
             HashMap<String, Object> map = new HashMap<>();
             map.put("status",HttpServletResponse.SC_NOT_FOUND);
             map.put("msg","username or password error");
                 webJsonUtil.senResponse(resp,map);

         };
    }

    public AuthenticationEntryPoint getPoint(){
         return (req,resp,exception)->{
             HashMap<String, Object> map = new HashMap<>();
             map.put("status",HttpServletResponse.SC_UNAUTHORIZED);
             map.put("msg","you must login first");
                 webJsonUtil.senResponse(resp,map);

         };
    }
}
