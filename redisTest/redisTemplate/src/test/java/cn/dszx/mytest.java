package cn.dszx;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
public class mytest {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Test
    public void test(){
//        redisTemplate.opsForValue().set("mytest","dszx");
//        System.out.println(redisTemplate.opsForValue().get("mytest"));
        HashOperations<String, Object, Object> stringObjectObjectHashOperations = redisTemplate.opsForHash();
//        stringObjectObjectHashOperations.put("myuser","name","dszx");
        stringObjectObjectHashOperations.put("myuser","age","18");
        Map<Object, Object> myuser = stringObjectObjectHashOperations.entries("myuser");
        System.out.println(myuser);

//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        String ping = connection.ping();
//        System.out.println(ping);

    }
    //清空刷新
    @Test
    public void mytest() {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                //redisConnection.flushDb();
                redisConnection.flushAll();
                return "flush ok";
            }
        });
    }
}
