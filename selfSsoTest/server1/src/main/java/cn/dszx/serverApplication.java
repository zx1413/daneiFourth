package cn.dszx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class serverApplication {
    public static void main(String[] args) {
        SpringApplication.run(serverApplication.class,args);
    }
    @RestController
    @RequestMapping("server")
    public class myController{
        @PreAuthorize("hasAuthority('sys:user:del')")
        @GetMapping("del")
        public String thdel(){
            return "this del";
        }
        @PreAuthorize("hasAuthority('sys:user:rev')")
        @GetMapping("rev")
        public String threv(){
            return "this rev";
        }
        @PreAuthorize("hasAuthority('sys:user:upd')")
        @GetMapping("upd")
        public String thupd(){
            return "this upd";
        }
        @PreAuthorize("hasAuthority('sys:user:add')")
        @GetMapping("add")
        public String thadd(){
            return "this add";
        }

    }
}
