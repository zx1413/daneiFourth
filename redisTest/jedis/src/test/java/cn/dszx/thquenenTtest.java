package cn.dszx;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class thquenenTtest {
    public static void put(String i){
        Jedis jedis = new Jedis("192.168.126.129", 6379);
//            jedis.lset();
            jedis.lpush("list",i);
            jedis.close();
    }
    public static String get(){

        Jedis jedis = new Jedis("192.168.126.129", 6379);
        String thget = jedis.rpop("list");
        jedis.close();
        return thget;
    }
    public static void main(String[] args) {
        new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=10;i++){//模拟页面上按钮点击
                    put(String.valueOf(i));
                    try{Thread.sleep(100);}catch(Exception e){}
                }
            }
        }.run();
//        new Thread(){
//            @Override
//            public void run() {
//                for(int i=1;i<=10;i++){//模拟页面上按钮点击
//                    put(String.valueOf(i));
//                    try{Thread.sleep(100);}catch(Exception e){}
//                }
//            }
//        }.start();
        new Thread(){
            @Override
            public void run() {
                for(;;){
                    String msg=get();
                    if(msg==null)continue;
                    System.out.print(msg);
                }
//                for (int i = 0; i < 10; i++) {
//                    System.out.print(get());
//
//                }
            }
        }.start();
//        new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    get();
//                }
//            }
//        }.run();
//        new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    put(String.valueOf(i));
//                }
//            }
//        }.run();

    }
}
