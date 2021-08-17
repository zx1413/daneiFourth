package cn.dszx.filter;

import javax.servlet.*;
import java.io.IOException;

public class myFliter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("this is filter");
    }
}
