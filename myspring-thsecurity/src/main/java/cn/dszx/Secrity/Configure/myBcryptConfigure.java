package cn.dszx.Secrity.Configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class myBcryptConfigure {
    @Bean
    public BCryptPasswordEncoder getBcrypt(){
        return new BCryptPasswordEncoder();
    }
}
