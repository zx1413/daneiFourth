package cn.dszx.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtsUtil {
    private static String key = "dszx.ink";
    public static String BcryEncode(Map<String,Object> map) {
       return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,key)
                .setExpiration(new Date(System.currentTimeMillis()+30*60*1000))
                .setClaims(map)
                .compact();
    }
    public static Claims openBcry(String str){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(str).getBody();
    }
    public Boolean overtime(String str) {
        return new Date().before(openBcry(str).getExpiration());
    }

}
