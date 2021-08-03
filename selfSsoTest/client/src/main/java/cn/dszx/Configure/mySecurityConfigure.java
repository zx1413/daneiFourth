package cn.dszx.Configure;

import cn.dszx.Utils.JwtsUtil;
import cn.dszx.Utils.ResponseUtil;
import com.sun.org.apache.xerces.internal.parsers.SecurityConfiguration;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class mySecurityConfigure extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin().loginProcessingUrl("/login").successHandler(getSuccess()).failureHandler(getFail());
        http.exceptionHandling().authenticationEntryPoint(getAuthen());
        http.authorizeRequests().antMatchers("/login").permitAll();
    }

    public AuthenticationEntryPoint getAuthen(){
        return (httpServletRequest, httpServletResponse, e) -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("state", HttpServletResponse.SC_UNAUTHORIZED);
            map.put("msg","please login first");
            ResponseUtil.thResponse(map,httpServletResponse);
        };
    }
    public AuthenticationFailureHandler getFail(){
        return (req,resp,exception) -> {
            HashMap<String, Object> map = new HashMap <>();
            map.put("status",HttpServletResponse.SC_NOT_FOUND);
            map.put("msg","username or password error");
            ResponseUtil.thResponse(map,resp);

        };
    }
    public AuthenticationSuccessHandler getSuccess(){
        return (req,resp,authentication)->{
            HashMap<String,Object> map = new HashMap<>();
            HashMap<String,Object> map1 = new HashMap<>();
            map.put("status",HttpServletResponse.SC_OK);
            map.put("msg","login in ok");
            User user = (User)authentication.getPrincipal();
            ArrayList<String> strings = new ArrayList<>();
            authentication.getAuthorities().forEach(x->strings.add(x.getAuthority()));
            map1.put("username",user.getUsername());
            map1.put("authen",strings);
            String jwts = JwtsUtil.getJwts(map1);
            map.put("allauthen",jwts);
            ResponseUtil.thResponse(map,resp);
        };
    }
}
