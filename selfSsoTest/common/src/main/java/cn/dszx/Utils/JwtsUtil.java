package cn.dszx.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtsUtil {
    private static String sort = "dszx.ink";
    public static String getJwts(Map<String,Object> map){
//        构建
        return Jwts.builder()
//               添加加密数据
                .addClaims(map)
//               生效时间
                .setExpiration(new Date(System.currentTimeMillis()+30*60*1000))
//               使用xx算法加密并使用加密盐
                .signWith(SignatureAlgorithm.HS256,sort)
//               最后生成
                .compact();
    }
    //    解密
    public static Claims getBody(String strJwts){
        return Jwts.parser()
//                放置加密盐
                .setSigningKey(sort)
//                添加解密数据
                .parseClaimsJws(strJwts)
//                获取的为body数据
//                生成的Jws<Claims>集合对象
//        header={alg=HS256},body={perssion=sys:user:cre,sys:user:del, username=dszx, exp=1627211460},signature=h71lG7fmRaBfa2chCYYbhBEJU8oYXBpWZeFNJ5FqV84
                .getBody();
    }
    //    判断是否过期
    public static Boolean upDate(String strJwts){
        Date expiration = getBody(strJwts).getExpiration();
        return expiration.before(new Date());
    }
}
