package com.xiaxin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MybatisMasterSlaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisMasterSlaveApplication.class, args);
    }

}
