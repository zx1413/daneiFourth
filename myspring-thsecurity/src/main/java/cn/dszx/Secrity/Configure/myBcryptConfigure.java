package cn.dszx.Secrity.Configure;

import cn.dszx.Secrity.Handle.mySuccessHandle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
//        设置表单拦截登录界面
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(new mySuccessHandle(""));
//                .successForwardUrl("/index")  ; //请求转发时使用，一般填Controller跳转 服务内部跳转
                //重定向跳转 请求和响应两次
//                .defaultSuccessUrl("/index.html");
//        设置放行请求界面
        http.authorizeRequests()
                .antMatchers("/login.html")
                .permitAll()
                .anyRequest().authenticated();
    }
    //    @Bean
//    public BCryptPasswordEncoder getBcrypt1(){
//        return new BCryptPasswordEncoder();
//    }
}
