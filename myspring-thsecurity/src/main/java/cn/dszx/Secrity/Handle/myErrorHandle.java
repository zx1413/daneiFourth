package cn.dszx.Secrity.Handle;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class myErrorHandle implements AuthenticationFailureHandler {


    private String url;
    public myErrorHandle(String url){
        this.url = url;
    }
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.sendRedirect(url);
//         httpServletRequest.getRequestDispatcher(url).forward(httpServletRequest,httpServletResponse);
    }
}
