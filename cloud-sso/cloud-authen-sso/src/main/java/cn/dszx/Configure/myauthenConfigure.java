package cn.dszx.Configure;


import cn.dszx.util.myJwtsUtil;
import cn.dszx.util.webJsonUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.*;

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
        http.authorizeRequests().antMatchers("/auth/getInfo","/login").permitAll();
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

               auth.getAuthorities().forEach(x->
                       list.add(x.getAuthority()));
               mymap.put("authen",list);
//               System.out.println("dszx");
//               System.out.println(list);
               User user = (User) auth.getPrincipal();
               mymap.put("username",user.getUsername());
               String jwts = myJwtsUtil.getJwts(mymap);
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
