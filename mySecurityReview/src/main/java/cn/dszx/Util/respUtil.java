package cn.dszx.Util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class respUtil {
    public static void sendResp(Map<String,Object> map, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        String s = new ObjectMapper().writeValueAsString(map);
        PrintWriter writer = response.getWriter();
        writer.write(s);
        writer.flush();

    }
}
