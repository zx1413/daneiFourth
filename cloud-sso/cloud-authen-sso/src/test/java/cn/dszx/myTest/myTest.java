package cn.dszx.myTest;

import cn.dszx.mapper.userMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class myTest {
    @Autowired
    userMapper userMapper;
    @Test
    public void test(){
        Map<String, Object> user1 = userMapper.getUser("admin");
        Long id = (Long)user1.get("id");
        String[] strings = userMapper.getAuthen(id).toArray(new String[]{});
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(userMapper.getAuthen(id).toArray(new String[]{}));
        grantedAuthorities.forEach(System.out::println);
        System.out.println(grantedAuthorities);

    }
}
