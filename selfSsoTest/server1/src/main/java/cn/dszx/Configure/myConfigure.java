package cn.dszx.Configure;

import cn.dszx.Handle.myHandle;
import cn.dszx.Utils.ResponseUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableGlobalMethodSecurity
public class myConfigure extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll();
        http.csrf().disable();
        http.exceptionHandling().accessDeniedHandler(getAuthen());
    }
    @Bean
    public AccessDeniedHandler getAuthen(){
        return (httpServletRequest, httpServletResponse, e) -> {
            Map<String,Object> map = new HashMap<>();
            map.put("msg","you have no permit");
            map.put("state", HttpServletResponse.SC_FORBIDDEN);
            ResponseUtil.thResponse(map,httpServletResponse);
        };
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new myHandle()).addPathPatterns("/**");
    }
}
