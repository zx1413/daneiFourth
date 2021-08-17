package cn.dszx;

import redis.clients.jedis.Jedis;

import java.util.*;

public class myssoTest {
//    public static boolean ifexits(){
//        Jedis jedis = new Jedis("192.168.126.129", 6379);
//        Map<String, String> map = jedis.hgetAll("session");
//        if (map.size() == 0){
//            System.out.println("用户没有登录");
//            return false;
//        }
//        Date date = new Date(Long.valueOf(map.get("expired")));
//        if (date.before(new Date())){
//            System.out.println("用户超时");
//            return false;
//        }
//        return true;
//    }
//    public static void login(String name,String password){
//        Map<String,String> map = new HashMap<>();
//        String token = UUID.randomUUID().toString().replace("-", "");
//        Calendar instance = Calendar.getInstance();
//        instance.add(Calendar.MINUTE,60);
//        map.put("token",token);
//        map.put("expired",String.valueOf(instance.getTimeInMillis()));
//        map.put("name",name);
//        Jedis jedis = new Jedis("192.168.126.129", 6379);
//        jedis.hset("session",map);
//    }
//    public static void main(String[] args) {
//        System.out.println("this is login");
//        boolean isok = ifexits();
//        if (isok){
//            System.out.println("访问允许");
//        }else {
//            login("admin","admin");
//        }
//    }

    public static boolean ifexits(String token){
    Jedis jedis = new Jedis("192.168.126.129", 6379);
//    Map<String, String> map = jedis.hgetAll("session");
        if (token == null){
            return false;
        }
    String token1 = jedis.hget("session", token);
    if (token1 == null || "".equals(token1)){
        System.out.println("用户没有登录");
        return false;
    }
    String expired = jedis.hget("session","expired");
    Date date = new Date(Long.valueOf(expired));
    if (date.before(new Date())){
        System.out.println("用户超时");
        return false;
    }
    return true;
}
    public static String login(String name,String password){
        Map<String,String> map = new HashMap<>();
        String token = UUID.randomUUID().toString().replace("-", "");
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE,60);
        map.put(token,name);
        map.put("expired",String.valueOf(instance.getTimeInMillis()));
//        map.put("name",name);
        Jedis jedis = new Jedis("192.168.126.129", 6379);
        jedis.hset("session",map);
        return token;
    }
    public static void main(String[] args) {
        System.out.println("this is login");
        String token = null;
        boolean isok = ifexits(token);
        if (!isok){
            System.out.println("登录");
           token = login("amdin","admin");
        }
        boolean test = ifexits(token);
        if (test){
            System.out.println("have login ok away");
        }


    }

}
