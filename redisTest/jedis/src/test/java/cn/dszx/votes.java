package cn.dszx;

import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class votes {
    private static Jedis jedis = new Jedis("192.168.126.129", 6379);
    public static void myadd(String key,String id){
        jedis.sadd(key,id);
    }
    public static Long getvote(String key){
        return jedis.scard(key);
    }
    public static Set<String> getSum(String key){
        return jedis.smembers(key);
    }
    public static boolean getexits(String key,String id){
         return jedis.sismember(key,id);
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        String mykey = "dszx";
        String first = "1";
        String second = "2";
        String third = "3";
        String forth = "4";
        myadd(mykey,first);

    }


}
