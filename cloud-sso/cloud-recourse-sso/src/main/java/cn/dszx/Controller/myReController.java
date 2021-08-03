package cn.dszx.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class myReController {
    @GetMapping("/thdel")
    @PreAuthorize("hasAuthority('sys:user:del')")
    public String del(){
        return "this is del";
    }
    @GetMapping("/thadd")
    @PreAuthorize("hasAuthority('sys:user:add')")
    public String add(){
        return "this is add";
    }

}
