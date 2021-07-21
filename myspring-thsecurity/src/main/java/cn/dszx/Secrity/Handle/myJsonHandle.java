package cn.dszx.Secrity.Handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class myJsonHandle implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        User details = (User)authentication.getPrincipal();
        System.out.println(details);
//        设置字符集编码格式
        httpServletResponse.setCharacterEncoding("utf-8");
//        设置客户端响应的内容及编码格式
        httpServletResponse.setContentType("application/json;charset=utf-8");
//        将响应的数据按照json流的形式输出
        PrintWriter out = httpServletResponse.getWriter();
//        输出json串
        Map<String,String> map = new HashMap<>();
        map.put("sex","nan");
        map.put("age","18");
        out.write(new ObjectMapper().writeValueAsString(map));
//        out.write("{\"sex\":\"nan\",\"age\":18}");
        out.flush();
    }
}
