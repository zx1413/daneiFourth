package cn.dszx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class redisTempleTest {
    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public void test(){
//        String key = "mykey";
        String key = UUID.randomUUID().toString().replace("-","");
//        设置锁 只能设置一次 重复设置返回 false
        try{
//            redisTemplate.expire(key,10, TimeUnit.SECONDS);
//            合并
            Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(key, "dszx",10,TimeUnit.SECONDS);
            System.out.println(key);
            System.out.println(redisTemplate.opsForValue().get(key));

            TimeUnit.SECONDS.sleep(10);
            System.out.println(aBoolean);

//        当其他线程抢占时，返回false，强制return
            if (!aBoolean){
                return;
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (key.equals(redisTemplate.opsForValue().get(key))){
                redisTemplate.delete(key);
            }
        }

//        System.out.println(aBoolean);
//        任务结束删除锁

    }


}
