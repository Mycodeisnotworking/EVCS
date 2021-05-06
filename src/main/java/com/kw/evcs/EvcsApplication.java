package com.kw.evcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EvcsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvcsApplication.class, args);
    }

}
