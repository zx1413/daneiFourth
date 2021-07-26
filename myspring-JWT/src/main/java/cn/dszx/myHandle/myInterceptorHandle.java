package cn.dszx.myHandle;

import cn.dszx.Utils.myJwtsUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class myInterceptorHandle implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token=request.getHeader("authen");
        System.out.println(token);


        if(!StringUtils.hasLength(token)) {
            throw new RuntimeException("please login");
        }

        if (myJwtsUtil.upDate(token)){
            throw new RuntimeException("login timeout,please login");
        }


       return true;
    }
}
