package cn.dszx.servlet;

import javax.servlet.*;
import java.io.IOException;

public class myServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        String id = servletRequest.getParameter("id");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.getWriter().write("Hello World" + id);
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
