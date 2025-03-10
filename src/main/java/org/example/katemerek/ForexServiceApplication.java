package org.example.katemerek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ForexServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForexServiceApplication.class, args);
    }

}
