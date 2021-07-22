package cn.dszx.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class myJwtsUtil {
    private static String sort = "dszx.ink";
    public String getJwts(Map<String,Object> map){
       return Jwts.builder()
               .addClaims(map)
               .setExpiration(new Date(System.currentTimeMillis()+30*60*1000))
               .signWith(SignatureAlgorithm.HS256,sort)
               .compact();
    }
    public Claims getBody(String strJwts){
        return Jwts.parser().parseClaimsJws(strJwts).getBody();
    }
    public Boolean upDate(String strJwts){
        Date expiration = getBody(strJwts).getExpiration();
        return expiration.before(new Date());
    }
}
