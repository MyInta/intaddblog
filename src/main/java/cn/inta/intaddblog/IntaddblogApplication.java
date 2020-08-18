package cn.inta.intaddblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.inta.intaddblog.mapper")
public class IntaddblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntaddblogApplication.class, args);
    }

}
