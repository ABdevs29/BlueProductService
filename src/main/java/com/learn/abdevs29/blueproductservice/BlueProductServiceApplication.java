package com.learn.abdevs29.blueproductservice;

import com.learn.abdevs29.blueproductservice.controllers.SampleController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BlueProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlueProductServiceApplication.class, args);
    }
}
