package cn.dszx.Factory;

import cn.dszx.Service.useRemoteProvider;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
//返回调用时的服务异常显示的数据
@Component
public class callbackFactory implements FallbackFactory<useRemoteProvider> {
    @Override
    public useRemoteProvider create(Throwable throwable) {
        System.out.println(throwable.getMessage()+"this is my test");
         return x-> "this is error";
//       return new useRemoteProvider() {
//            @Override
//            public String getFeign(String msg) {
//                System.out.println("this is mytest");
//               return "mytest";
//            }
//        };
    }
}
