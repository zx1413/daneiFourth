package cn.dszx.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//实行实时刷新
//@Scope 表明为单例
//自动动态刷新，创建新的实例
@RefreshScope
@RestController
public class proviceController {
//    配置日志打印信息
    private static final Logger mylog = LoggerFactory.getLogger(proviceController.class);
    @Value("${server.port}")
    private String server;
//    获取日志错误信息
    @Value("${logging.level.cn.dszx:debug}")
    private String levelMsg;

    @Value("${server.tomcat.threads.max:1}")
    private int maxThread;

    @Value("${page.pageSize:0}")
    private String size;



    @GetMapping("/provide/getThreads")
    public String getThreads() throws Exception{
        String thname = Thread.currentThread().getName();
        Thread.sleep(50000);
        System.out.println(thname);
        return "the threads is: " + maxThread;
    }

    @GetMapping("/provide/getSize")
    public String getPage(){
        return "the page is : " + size;
    }

//    public ProviderController(){
//        System.out.println("ProviderController()");
//    }

//    @GetMapping("/provider/maxThread")
//    public String doGetMaxThread() throws Exception{
//
//        return "max thread is "+ thname;
//    }
    @GetMapping("/provide/mygetLogging")
    public String getLogg(){
//        按优先级别输出   其输出取决于 nacos 中的配置内容  若配置级别低于上面的 无法显示以上的内容
        mylog.trace("");
        mylog.debug("");
        mylog.warn("");
        mylog.info("");
        mylog.error("");
        return "my logging type: "+ levelMsg;
    }
    @GetMapping(value = "/provider/echo/{msg}")
    public String getTest(@PathVariable("msg")String msg) throws InterruptedException {
//        System.out.println("dszx");
//        Thread.sleep(500000);
        if(msg.length()<3) {
            throw new IllegalArgumentException();
        }
      return server +" this is myprovider: " + msg;
    }
}
