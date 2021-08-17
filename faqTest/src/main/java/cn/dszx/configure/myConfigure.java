package cn.dszx.configure;

import cn.dszx.filter.myFliter;
import cn.dszx.listen.Mylisten;
import cn.dszx.servlet.myServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class myConfigure {
    @Bean
    public ServletRegistrationBean getServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.addUrlMappings("/servlet");
        servletRegistrationBean.setServlet(new myServlet());

//        servletRegistrationBean.
        return servletRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean getFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.addUrlPatterns("/filter");
        filterRegistrationBean.setFilter(new myFliter());
        return filterRegistrationBean;
    }
    @Bean
    public ServletListenerRegistrationBean getListen(){
        return new ServletListenerRegistrationBean(new Mylisten());
    }

}
