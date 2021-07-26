package cn.dszx.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class myController {
    @PreAuthorize("hasAuthority('sys:user:del')")
    @RequestMapping("/del")
    public String mydel(){
        return "this is del";
    }
    @PreAuthorize("hasAuthority('sys:user:reviece')")
    @RequestMapping("/rev")
    public String reviece(){
        return "this is reviece";
    }

}
