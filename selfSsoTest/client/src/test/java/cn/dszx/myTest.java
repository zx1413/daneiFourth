package cn.dszx;





import cn.dszx.Mapper.userMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@SpringBootTest
public class myTest {
    @Autowired
    cn.dszx.Mapper.userMapper userMapper;
    @Test
    public void test() throws SQLException {
        List<String> authen = userMapper.getAuthen(1L);
        System.out.println(authen);
    }
}
