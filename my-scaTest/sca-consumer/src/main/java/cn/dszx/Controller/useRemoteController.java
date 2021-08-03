package cn.dszx.Controller;


import cn.dszx.Service.useRemoteProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class useRemoteController {
    @Autowired
    cn.dszx.Service.useRemoteProvider useRemoteProvider;
    @GetMapping("/consumer/getRemoteFeign/{msg}")
    public String mytest(@PathVariable("msg") String msg){
        return useRemoteProvider.getFeign(msg);
    }}
