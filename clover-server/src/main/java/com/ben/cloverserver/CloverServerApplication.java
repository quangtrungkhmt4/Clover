package com.ben.cloverserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CloverServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloverServerApplication.class, args);
    }

}
