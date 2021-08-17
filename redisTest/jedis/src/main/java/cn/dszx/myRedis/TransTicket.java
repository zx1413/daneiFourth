package cn.dszx.myRedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

import java.util.List;

public class TransTicket {
    public static void main(String[] args) {

        new Thread(() -> {
            System.out.println("user1 :"+ getTicket());

        }).start();
        new Thread(() -> {
            System.out.println("user2 :" + getTicket());
            getTicket();
        }).start();
    }
    public static String getTicket(){
        JedisPoolConfig config = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(config,"192.168.126.129",6379);
        Jedis resource = jedisPool.getResource();
        resource.set("ticket","1");
        resource.watch("ticket");
        String ticket = resource.get("ticket");
        if (ticket!=null && Integer.valueOf(ticket )!=0){
            Transaction multi = resource.multi();
            try {
                multi.decr("ticket");
                List<Object> exec = multi.exec();
                if (exec == null){
                    return "you don't get ticket";
                }else {
                    return "you get ticket";
                }
            }catch (Exception e){
                e.printStackTrace();
                multi.discard();
            }finally {
                resource.close();
            }
        } else {
            System.out.println("null");
            throw new RuntimeException("there is no ticket");
        }
        return null;

    }

}
