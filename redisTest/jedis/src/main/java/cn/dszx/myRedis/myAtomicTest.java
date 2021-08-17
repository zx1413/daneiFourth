package cn.dszx.myRedis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class myAtomicTest {
    private static final  int SIZE = 10;
    private static int sum = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static void main(String[] args) throws Exception{
        for (int i = 0; i < SIZE; i++) {
            new Thread(()->{
                for (int j = 0; j < SIZE; j++) {
                    add();
                }
            }).start();
        }
//        TimeUnit.SECONDS.sleep(5);
        System.out.println(atomicInteger.get());
    }
    public static void add(){
        atomicInteger.incrementAndGet();
    }
}
