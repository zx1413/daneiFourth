package cn.dszx.Secrity.Handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class myErrorAuthen implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");

        PrintWriter writer = httpServletResponse.getWriter();
        Map<String,Object> map = new HashMap<>();
        map.put("status",HttpServletResponse.SC_UNAUTHORIZED);
        map.put("msg","you have no authenty");
        writer.write(new ObjectMapper().writeValueAsString(map));
        writer.flush();
    }
}
