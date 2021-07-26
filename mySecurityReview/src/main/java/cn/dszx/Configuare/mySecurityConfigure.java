package cn.dszx.Configuare;

import cn.dszx.Util.JwtsUtil;
import cn.dszx.Util.respUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
public class mySecurityConfigure extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder thBcry(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        设置跨域
        http.csrf().disable();
//        http.formLogin().loginProcessingUrl("/login").loginPage("/mylogin.html").usernameParameter("myname").passwordParameter("mypassword");
        http.formLogin().loginProcessingUrl("/login").successHandler(getsuccess()).failureHandler(getfaile());
//        http.authorizeRequests()
//                .antMatchers("/mylogin.html")
//                .permitAll()
//                .anyRequest()
//                .authenticated();
        http.authorizeRequests().anyRequest().permitAll();

    }
    public AuthenticationFailureHandler getfaile(){
        return (req,resp,exception)->{
            HashMap<String,Object> map = new HashMap<>();
            map.put("status",HttpServletResponse.SC_UNAUTHORIZED);
            map.put("msg","username or password error");
            respUtil.sendResp(map,resp);
        };
    }
    public AuthenticationSuccessHandler getsuccess(){
        return (req,resp,authen)->{
//            向浏览器发送的相关信息
            HashMap<String, Object> map = new HashMap<>();
            map.put("status", HttpServletResponse.SC_OK);
            map.put("msg","login ok");
            User user = (User)authen.getPrincipal();
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("username",user.getUsername());
            List<String> list = new ArrayList<>();
            System.out.println(authen);
            authen.getAuthorities().forEach(x->list.add(x.getAuthority()));
            String s = JwtsUtil.BcryEncode(map1);
            map1.put("authen",s);
            map.put("usermsg",map1);
            respUtil.sendResp(map,resp);
        };
    }

}
