package cn.dszx;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
@SuppressWarnings({"all"})
public class myTest {
    //修改reids中的redis.conf相关配置
    //关闭 bind 127.0.0.1
    //修改为 protected-mode no

//        static AtomicReference<Thread> atomicReference=new AtomicReference<Thread>();

        public static void main(String[] args) {
            HashMap map = new HashMap();
            for (int i = 0; i < 15; i++) {
                map.put(new A(i), "hello");
            }
            System.out.println(map);
//            Collection<String> values = map.values();
//            Iterator<String> iterator = values.iterator();
//            iterator.forEachRemaining(x-> System.out.println(x));
//
////            System.out.println(atomicReference);
//            AtomicInteger atomicInteger = new AtomicInteger(10);
//            for (int i = 0; i < 5; i++) {
//                atomicInteger.incrementAndGet();
//            }
//            System.out.println(atomicInteger.get());
//            boolean b = atomicInteger.compareAndSet(15, 10);
//            System.out.println(atomicInteger.get());
//            System.out.println(b);
//
//            myTest  myTest = new myTest();
//            new Thread(()->{
//                myTest.myLock();
//                try {
//                    TimeUnit.SECONDS.sleep(5);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                myTest.myUnLock();
//            },"AA").start();
//
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            new Thread(()->{
//                myTest.myLock();
////                try {
////                    TimeUnit.SECONDS.sleep(1);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//                myTest.myUnLock();
//            },"BB").start();
//
//
//        }
//        void mytest(){
//            System.out.println(Thread.currentThread().getName());
//        }
//        void myLock(){
//            Thread thread = Thread.currentThread();
//            System.out.println(Thread.currentThread().getName()+"\t come in");
//            while(!atomicReference.compareAndSet(null,thread));
//        }
//        void myUnLock(){
//            Thread thread = Thread.currentThread();
//            atomicReference.compareAndSet(thread,null);
//            System.out.println(Thread.currentThread().getName()+"\t invoked myUnLock");
//        }

//        ReentrantLock reentrantLock = new ReentrantLock();
//        reentrantLock.lock();
//        new Thread(()->{
//            System.out.println("two");
//        }).start();
//        new Thread(()->{
//            System.out.println("one");
//        }).start();


//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(10);
//        jedisPoolConfig.setMinIdle(1);
//        jedisPoolConfig.setMaxTotal(10);
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"192.168.126.129",6379);
//        Jedis resource = jedisPool.getResource();
//        Pipeline pipelined = resource.pipelined();
//        pipelined.set("one","first");
//        pipelined.set("two","second");
//        List<Object> objects = pipelined.syncAndReturnAll();
//        System.out.println(objects);
//
//        new Thread(() -> {
//            System.out.println("dszx");
//            System.out.println(Thread.currentThread().getName());
//        });
//        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName());
//        });


//        Jedis jedis = new Jedis("192.168.126.129", 6379);
//        String mytest = jedis.set("mytest", "1");
//        jedis.incrBy("mytest",10);
//        jedis.expire("mytest",5);
//        TimeUnit.SECONDS.sleep(5);
//
////        Thread.sleep(5000);
//        System.out.println(jedis.get("mytest"));
//        Map<String,String> map = new HashMap<>();
//        map.put("name","dszx");
//        map.put("age","18");
//        map.put("sex","nan");
//        Gson gson = new Gson();
//        String s = gson.toJson(map);
//        jedis.set("blog",s);
//        System.out.println(jedis.get("blog"));

//        String key = "blog";
//        jedis.hset(key,"name","dszx");
//        jedis.hset(key,"age","18");
//        jedis.hset(key,"sex","name");
//        jedis.rpush("list","1","2","3","4");
//        jedis.brpop(60,"list");
//        jedis.brpop(60,"list");
//        jedis.brpop(60,"list");
//        jedis.brpop(60,"list");
//        System.out.println("finally");
//        jedis.brpop(60,"list");
//        jedis.close();
//            Collections.
            List<String> list = Arrays.asList("implement","extend","newtest","object","value","int");
            String max = Collections.max(list, (x, y) -> x.length() - y.length());
            System.out.println(max);


        }
}
class A{

    @Override
    public int hashCode() {
        return 100;
    }

    private int num;

    public A(int num) {
        this.num = num;
    }

}
