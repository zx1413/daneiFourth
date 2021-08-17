package cn.dszx;


import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.List;

public class masterTest {
    @Test
    public void test() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);
        config.setMaxIdle(5);
        config.setMinIdle(1);
        JedisPool jedisPool = new JedisPool(config, "192.168.126.129", 6379);
        Jedis resource = jedisPool.getResource();
        Pipeline pl = resource.pipelined();
        for (int i = 0; i < 5; i++) {
            pl.incr("pipelineKey");
            pl.set("zhuge" + i, "zhuge");
        }
        List<Object> results = pl.syncAndReturnAll();
        System.out.println(results);
    }
}
