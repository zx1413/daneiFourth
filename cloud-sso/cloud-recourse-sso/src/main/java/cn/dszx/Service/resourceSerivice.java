package cn.dszx.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/***
 * name 服务的类型 contextId 给注入的 bean类型写入唯一id（多个@FeignClient需要 只有一个时可以省略） fallbackFactory 服务中断返回的异常
 */
@FeignClient(name = "cloud-sso-authen",contextId="recourseService")
public interface resourceSerivice {
    //与其他服务器的Controller的url一致 即可返回其他服务器数据
    @GetMapping("/auth/getInfo")
    Map<String,Object> getFeign(@RequestParam("token") String token);

}
