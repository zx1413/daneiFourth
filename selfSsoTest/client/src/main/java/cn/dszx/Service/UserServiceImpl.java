package cn.dszx.Service;

import cn.dszx.Mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.lang.reflect.Array;
import java.util.Map;

public class UserServiceImpl implements UserDetailsService {
    @Autowired
    cn.dszx.Mapper.userMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Map<String, Object> user = userMapper.getUser(s);
        if (user == null) {
            throw new UsernameNotFoundException("user is null");
        }
        return new User(s,(String) user.get("password"),AuthorityUtils.createAuthorityList(userMapper.getAuthen((Long)user.get("id")).toArray(new String[]{})));
    }
}
