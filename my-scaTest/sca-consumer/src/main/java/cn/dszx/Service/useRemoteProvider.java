package cn.dszx.Service;

import cn.dszx.Factory.callbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/***
 * name 服务的类型 contextId 给注入的 bean类型写入唯一id fallbackFactory 服务中断返回的异常
 */
@FeignClient(name = "sca-provider",contextId="useRemoteProvider",fallbackFactory = callbackFactory.class)
public interface useRemoteProvider {
    @GetMapping("/provider/echo/{msg}")
    String getFeign(@PathVariable("msg")String msg);

}
