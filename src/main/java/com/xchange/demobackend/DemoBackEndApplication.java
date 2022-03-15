package com.xchange.demobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DemoBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoBackEndApplication.class, args);
    }

}
