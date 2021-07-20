package cn.dszx.Secrity.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class myController {
    @RequestMapping("/index")
    public String test(){
        return "redirect:/index.html";
    }
}
