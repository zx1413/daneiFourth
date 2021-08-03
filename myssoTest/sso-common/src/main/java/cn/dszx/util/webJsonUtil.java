package cn.dszx.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class webJsonUtil {
    public static void senResponse(HttpServletResponse response, Map<String,Object> map) throws
            IOException  //调用的方法中存在内置接口存在抛出 IOException 异常 若填其他 调用的方法还需再次抛出异常
    {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        String s = new ObjectMapper().writeValueAsString(map);
        PrintWriter writer = response.getWriter();
        writer.write(s);
        writer.flush();
    }
}
