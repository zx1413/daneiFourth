package sso.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsFilterConfig {
    @Bean
    public FilterRegistrationBean<CorsFilter> filterFilterRegistrationBean(){
        //1.对此过滤器进行配置(跨域设置-url,method)
        UrlBasedCorsConfigurationSource configSource=new UrlBasedCorsConfigurationSource();
        CorsConfiguration config=new CorsConfiguration();
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");//post,delete,get,put,....
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        //2.注册过滤器并设置其优先级
        configSource.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> fBean=
                new FilterRegistrationBean(new CorsFilter(configSource));
        fBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return fBean;
    }

}
