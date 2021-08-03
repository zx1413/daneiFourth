package cn.dszx.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class ResponseUtil {
    public static void thResponse(Map<String,Object> map,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
//        放回json字符串
        String s = new ObjectMapper().writeValueAsString(map);
        writer.write(s);
        writer.flush();
    }
}
