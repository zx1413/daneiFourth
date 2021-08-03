package cn.dszx;




import cn.dszx.mapper.userMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.sql.SQLException;


@SpringBootTest
public class jdbcTest {
   @Autowired
   private DataSource dataSource;
   @Autowired
   cn.dszx.mapper.userMapper userMapper;
   @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
//    @Autowired
//    cn.dszx.mapper.userMapper userMapper;
    @Test
    public void test() throws SQLException {
//        System.out.println(userMapper.getUser("admin"));
//        System.out.println(userMapper.selectList(null));
//        System.out.println(dataSource.getConnection());
//        System.out.println(userMapper.getUser("admin"));
//        System.out.println(bCryptPasswordEncoder.encode("123456"));
//        System.out.println(userMapper.getAuthen(1L));
        System.out.println(dataSource.getConnection());
    }
}
