package cn.dszx;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class mypooltest {
    @Test
    public void test(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1000);//最大连接数
        config.setMaxIdle(60);//最大空闲时间(连接不用了要释放)
        //创建连接池
        JedisPool jedisPool = new JedisPool(config,"192.168.126.129", 6379);
        Jedis resource = jedisPool.getResource();
        resource.close();
        resource.close();
    }
}
