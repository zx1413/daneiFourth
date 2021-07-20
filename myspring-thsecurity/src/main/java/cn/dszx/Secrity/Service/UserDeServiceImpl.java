package cn.dszx.Secrity.Service;

import cn.dszx.Secrity.Configure.myBcryptConfigure;
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
public class UserDeServiceImpl implements UserDetailsService {
    @Autowired
    BCryptPasswordEncoder getBcrypt;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (!s.equals("admin")) {
            throw new UsernameNotFoundException("user not exit");
        }
        String name = "admin";
        String pwd = "123456";
//        String newpwd = getBcrypt.encode(pwd);
//        System.out.println(newpwd);
//        数据库获取的限权
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin,ROLE_normal,sys:res:retrieve,sys:res:create");
        User user = new User(name,getBcrypt.encode(pwd), grantedAuthorities);
        return user;
    }
}
