package com.yh.yygh.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ServerMsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerMsmApplication.class,args);
    }
}
