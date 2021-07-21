package cn.dszx.Secrity.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class myController {
    @RequestMapping("/index")
    public String test(){
        return "redirect:/index.html";
    }
//    ROLE_admin,ROLE_normal,sys:res:retrieve,sys:res:create
//    页面若出现 403 则表示为 无权限访问
//    添加访问限权 role 用户名限定访问   Authority 该用户下拥有的Authority(限权)
    @PreAuthorize("hasAnyRole('jack')")
    @ResponseBody
    @RequestMapping("/select")
    public String create(){
        return "create";
    }
    @PreAuthorize("hasAuthority('sys:res:retrieve')")
    @ResponseBody
    @RequestMapping("/add")
    public String add(){
        return "add";
    }
    @ResponseBody
    @RequestMapping("/update")
    public String update(){
        return "update";
    }
    @ResponseBody
    @RequestMapping("/delete")
    public String delete(){
        return "delete";
    }
}
