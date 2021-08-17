package cn.dszx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class masterTest {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Test
    public void test(){

//        redisTemplate.opsForValue().set("master","6379");
        System.out.println(redisTemplate.opsForValue().get("master"));
    }
}
