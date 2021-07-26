package cn.dszx;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JwtTest {
    @Test
    public void test(){
//        添加加密盐
        String sort = "cn.dszx.mytest";
//        JwtBuilder builder = Jwts.builder();
        Map<String,Object> map = new HashMap<>();
        map.put("username","dszx");
        map.put("perssion","sys:user:cre,sys:user:del");
//        String compact = Jwts.builder()
////                添加加密信息
//                .setClaims(map)
////                添加有效时间 到某个时间后失效
////                .setExpiration(new Date(System.currentTimeMillis()+30*1000))
//                .setExpiration(new Date(System.currentTimeMillis() + 30 * 1000))
////                创建时间
////                .setIssuedAt(new Date())
////                添加加密算法
//                .signWith(SignatureAlgorithm.HS256, sort)
////                生成对象
//                .compact();
//        System.out.println(compact);
//        Claims body = Jwts.parser().setSigningKey(sort).parseClaimsJws(compact).getBody();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,30);
        Date time = calendar.getTime();
        String compact = Jwts.builder().addClaims(map).setExpiration(time).signWith(SignatureAlgorithm.HS256, sort).compact();
        System.out.println(compact);
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(sort).parseClaimsJws(compact);
        System.out.println(claimsJws);
//        System.out.println(claimsJws);
//
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.add(Calendar.MINUTE,30);
//        Date time1 = calendar.getTime();


    }
}
