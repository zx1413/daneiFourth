package cn.dszx.Configure;


import cn.dszx.Handle.myTokenInterceptor;
import cn.dszx.Service.resourceSerivice;
import cn.dszx.util.webJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class ResourceConfigure extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    @Autowired
    cn.dszx.Service.resourceSerivice resourceSerivice;
    @Autowired
    RestTemplate restTemplate;

    @LoadBalanced
    @Bean
    public RestTemplate getTemp(){
        return new RestTemplate();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //1.关闭跨域攻击
        http.csrf().disable();
        //2.设置拒绝处理器(不允许访问资源时,应该给出什么反馈)
        http.exceptionHandling().accessDeniedHandler(accessDen());
        //3.资源访问(所有资源在本项目中的访问不进行认证)
        http.authorizeRequests().anyRequest().permitAll();
    }
    public AccessDeniedHandler accessDen(){
        return (req,resp,e)->{
            Map<String,Object> map = new HashMap();
            map.put("status", HttpServletResponse.SC_FORBIDDEN);
            map.put("msg","you have no permit");
            webJsonUtil.senResponse(resp,map);
        };
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new myTokenInterceptor(restTemplate))
                .addPathPatterns("/**");
    }
}
