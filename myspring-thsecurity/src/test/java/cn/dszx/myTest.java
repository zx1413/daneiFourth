package cn.dszx;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class myTest {
    @Test
    public void test(){
       String pwd = "123456";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String newpwd =
                bCryptPasswordEncoder.encode(pwd);
        boolean matches = bCryptPasswordEncoder.matches(pwd, newpwd);
        System.out.println(matches);


    }
}
