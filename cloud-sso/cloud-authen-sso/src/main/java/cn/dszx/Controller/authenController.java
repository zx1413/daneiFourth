package cn.dszx.Controller;

import cn.dszx.mapper.userMapper;
import cn.dszx.util.myJwtsUtil;
import io.jsonwebtoken.Claims;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class authenController {
    @Autowired
    cn.dszx.mapper.userMapper userMapper;
    @GetMapping("/auth/getInfo")
    public Map<String,Object> getAuthentication(@RequestParam("token") String token) {
        Logger logger = LoggerFactory.getLogger(authenController.class);

        Claims claims = myJwtsUtil.getBody(token);
//        System.out.println(claims);
        claims.forEach((x,y)->logger.info(x+":"+y));
        boolean flag = claims.getExpiration().before(new Date());
        String username = (String) claims.get("username");
        List<String> list = (List<String>)
                claims.get("authen");
        Map<String, Object> map = new HashMap<>();
//        System.out.println(list);
//        Map<String, Object> user = userMapper.getUser(username);

        map.put("thTime", flag);
        map.put("username", username);
        map.put("authen", list);
//        map.put("password",user.get("password"));
        return map;
    }
}
