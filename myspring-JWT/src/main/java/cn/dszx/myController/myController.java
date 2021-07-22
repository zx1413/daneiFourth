package cn.dszx.myController;

import cn.dszx.Utils.myJwtsUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class myController {
    @PostMapping("/getUser")
    public Map<String,Object> getUser(String username,String password){
        Map<String,Object> map = new HashMap<>();
        if ("admin".equals(username) && "123456".equals(password)){
            map.put("state","200");
            map.put("msg","success");
            Map<String,Object> map1 = new HashMap<>();
            map1.put("username",username);
            map.put("authen",new myJwtsUtil().getJwts(map1));
            return map;
        }else {
            map.put("state","400");
            map.put("msg","error");
            return map;
        }
    }
    @GetMapping("/update")
    public String getMess() {
        return "this is update";
    }
}
