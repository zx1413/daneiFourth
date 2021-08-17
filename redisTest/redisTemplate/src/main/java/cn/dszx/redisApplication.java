package cn.dszx;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class redisApplication {
    public static void main(String[] args) {
        SpringApplication.run(redisApplication.class);

    }


    @Bean
    public Redisson getRedisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.126.129:6379").setDatabase(0);
        return (Redisson) Redisson.create(config);

    }
}
