package cn.dszx;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * redis秒杀练习:
 * 模拟两个线程都去抢购同一张票(考虑乐关锁)
 */
public class daineiforht {

      public static void secKill(){
          Jedis jedis=new Jedis("192.168.126.129",6379);
//          jedis.auth("123456");
          jedis.watch("ticket","money");
          String ticket = jedis.get("ticket");
          if(ticket==null||Integer.valueOf(ticket)==0)
              throw new RuntimeException("已无库存");
          Transaction multi = jedis.multi();
          try {
              multi.decr("ticket");
              multi.incrBy("money", 100);
              multi.exec();
          }catch (Exception e){
              e.printStackTrace();
              multi.discard();
          }finally {
              jedis.unwatch();
              jedis.close();
          }
      }
      static void print(){
          Jedis jedis1=new Jedis("192.168.126.129",6379);
//          jedis1.auth("123456");
          String ticket=jedis1.get("ticket");
          String money=jedis1.get("money");
          String tName=Thread.currentThread().getName();
          System.out.println(tName+"->"+ticket+"/"+money);
          jedis1.close();
      }
      public static void main(String[] args) {
          Jedis jedis=new Jedis("192.168.126.129",6379);
//          jedis.auth("123456");
          jedis.set("ticket","1");
          jedis.set("money","0");
//        Thread t0=new Thread(new Runnable() {
//              @Override
//              public void run() {
//              }
//        });
          Thread t1=new Thread(()->{
              secKill();
              //print();
          });
          Thread t2=new Thread(()->{
              secKill();
             // print();
          });
          t1.start();
          t2.start();
      }
}
