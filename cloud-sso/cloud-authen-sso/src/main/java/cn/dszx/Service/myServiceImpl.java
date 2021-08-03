package cn.dszx.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class myServiceImpl implements UserDetailsService {
//    @Autowired
//    cn.dszx.mapper.userMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    cn.dszx.mapper.userMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        Logger logger = LoggerFactory.getLogger(myServiceImpl.class);
//        new ArrayList<>();
//                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin,ROLE_dszx,sys:user:del,sys:user:update");
//        LambdaQueryWrapper<cn.dszx.pojo.User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq(cn.dszx.pojo.User::getUsername,s);
//        cn.dszx.pojo.User user1 = userMapper.selectOne(lambdaQueryWrapper);
        Map<String, Object> user1 = userMapper.getUser(s);
        Long id = (Long)user1.get("id");
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(userMapper.getAuthen(id).toArray(new String[]{}));
//       userMapper.getAuthen(id).forEach(x->{
//           grantedAuthorities.add(x.)
//       });

        User user = new User(s,(String)user1.get("password"),grantedAuthorities);

//        QueryWrapper<User> queryWrapper = new QueryWrapper();
//        queryWrapper.eq("username",s);
//
//        User user  = userMapper.selectOne(queryWrapper);
//        new org.springframework.security.core.userdetails.User(user.ge);

//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(User::getUsername,s);
//        User user = userMapper.selectOne(queryWrapper);

        return user;
    }
}
