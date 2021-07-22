package cn.dszx.Secrity.Handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class myaccessDenidedHandle implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");

        PrintWriter writer = httpServletResponse.getWriter();
        Map<String,Object> map = new HashMap<>();
        map.put("status",HttpServletResponse.SC_FORBIDDEN);
        map.put("msg","please login first");
        writer.write(new ObjectMapper().writeValueAsString(map));
        writer.flush();

    }
}
