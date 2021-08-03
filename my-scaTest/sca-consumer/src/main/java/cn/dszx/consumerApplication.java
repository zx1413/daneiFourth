package cn.dszx;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicLong;

@EnableFeignClients
@SpringBootApplication
public class consumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(consumerApplication.class,args);
    }
//    设置关联链路
    @Service
    public class Sentinel{
        @SentinelResource
        public String getSentinel(){
            return "get sentinelTest";
        }
    }


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
//    在导入的同时配置负载均衡
    @Bean
    @LoadBalanced
    public RestTemplate restTemplateBalance(){
        return new RestTemplate();
    }
    @RestController
    public class consumerController{
        @Autowired
        Sentinel sentinel;
        @Autowired
        RestTemplate restTemplate;
//        负载均衡策略原生自带,不用注入
        @Autowired
        RestTemplate restTemplateBalance;
        @Autowired
        LoadBalancerClient loadBalancerClient;

        @Value("${spring.application.name}")
        private String serverName;
//        类似乐观锁的变量 普通变量线程不安全
        private AtomicLong atomicLong = new AtomicLong(1);
        @GetMapping("/consumer/getprovide")
        public String getServerName(){
            String url = "http://localhost:8081/provider/echo/"+serverName;
            System.out.println("request url:"+url);
            //    填对应其他服务器的网址,显示返回其他服务器的响应数据
            return restTemplate.getForObject(url, String.class);
        }
        @GetMapping("/consumer/getprovideBalance")
        public String getServerName1(){
            sentinel.getSentinel();
//            负载获取服务器名称
            ServiceInstance choose = loadBalancerClient.choose("sca-provider");
//            String url = "http://localhost:8081/provider/echo/"+serverName;
            String url = String.format("http://%s:%s/provider/echo/%s",choose.getHost(),choose.getPort(),serverName);
            System.out.println("request url:"+url);
            //    填对应其他服务器的网址,显示返回其他服务器的响应数据
            return restTemplate.getForObject(url, String.class);
        }
        @GetMapping("/consumer/getprovideBalance1")
        public String getServerName11(){
//            先获取再自增
            long andIncrement = atomicLong.getAndIncrement();
//            先自增再获取
//            atomicLong.incrementAndGet();
//            if (andIncrement%2 == 0) {
//                throw new RuntimeException("this is error");
//            }
//            负载获取服务器名称
            return restTemplateBalance.getForObject(String.format("http://%s/provider/echo/%s","sca-provider",serverName),String.class);
            //    填对应其他服务器的网址,显示返回其他服务器的响应数据
//            return restTemplate.getForObject(url, String.class);
        }
        @GetMapping("/comsumer/getId")
        @SentinelResource("doFindById")
        public String doFindById(@RequestParam("id") Integer id){
            return "hot id is "+id;
        }
    }
}
