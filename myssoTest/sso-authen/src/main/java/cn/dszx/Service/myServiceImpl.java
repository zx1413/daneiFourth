package cn.dszx.Service;

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

@Service
public class myServiceImpl implements UserDetailsService {
//    @Autowired
//    cn.dszx.mapper.userMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin,ROLE_dszx,sys:user:del,sys:user:update");
//
        User user = new User(s,bCryptPasswordEncoder.encode("123456"),grantedAuthorities);
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
