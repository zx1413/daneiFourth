package cn.dszx.Secrity.Configure;

import cn.dszx.Secrity.Handle.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;

//这个配置类是配置Spring-Security的,
//prePostEnabled= true表示启动权限管理功能
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class myBcryptConfigure extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder getBcrypt(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        关闭跨域
        http.csrf().disable();


        http.exceptionHandling()
                .accessDeniedHandler(new myaccessDenidedHandle())
                .authenticationEntryPoint(new myErrorAuthen());
        //        设置表单拦截登录界面
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
//                .successHandler(new ForwardAuthenticationSuccessHandler("/index.html"))
                .successHandler(new mySuccessHandle("/index.html"))
//                .successHandler(new myJsonHandle())
                .failureHandler(new myErrorHandle("/errorpage.html"));

//                .successForwardUrl("/index")  ; //请求转发时使用，一般填Controller跳转 服务内部跳转
                //重定向跳转 请求和响应两次
//                .defaultSuccessUrl("/index.html");
//        设置放行请求界面 请求限权
        http.authorizeRequests()
                .antMatchers("/login.html","/errorpage.html")
                .permitAll()
                .anyRequest().authenticated();
    }
    //    @Bean
//    public BCryptPasswordEncoder getBcrypt1(){
//        return new BCryptPasswordEncoder();
//    }
}
