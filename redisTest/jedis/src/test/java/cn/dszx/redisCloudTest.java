package cn.dszx;


import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class redisCloudTest {
    public static void main(String[] args) {
        Set<HostAndPort> ports = new HashSet<>();
        Collections.addAll(ports,
                new HostAndPort("192.168.126.129", 8010),
                new HostAndPort("192.168.126.129", 8012),
                new HostAndPort("192.168.126.129", 8013),
                new HostAndPort("192.168.126.129", 8014),
                new HostAndPort("192.168.126.129", 8015)
        );
        JedisCluster jedisCluster = new JedisCluster(new HostAndPort("192.168.126.129", 8015));
        //使用jedisCluster操作redis
        jedisCluster.set("test", "cluster");
        String str = jedisCluster.get("test");
        System.out.println(str);
        //关闭连接池
        jedisCluster.close();
    }

}
