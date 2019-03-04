package cn.education.web.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;


@EnableAutoConfiguration()
@EnableTransactionManagement
@ComponentScan(basePackages = { "cn.education.web"})
@MapperScan(basePackages = "cn.education.web.mapper")
public class StartWeb {

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(StartWeb.class);
        application.run(args);
    }


}
