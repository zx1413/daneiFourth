package cn.dszx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.lang.reflect.Array;
import java.util.Arrays;

@SpringBootApplication
public class myapplication {
    public static void main(String[] args) {
        SpringApplication.run(myapplication.class,args);
        test();
    }
    public static void test(){
        for (int i = 0; i < 20; i++) {
            String pass = new BCryptPasswordEncoder().encode("123456");
//            String s = DigestUtils.md5DigestAsHex("123456".getBytes());
//            System.out.println(s);
            System.out.println(pass);
        }

    }
}
