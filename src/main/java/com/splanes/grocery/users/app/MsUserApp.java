package com.splanes.grocery.users.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MsUserApp {
    public static void main(String[] args) {
        SpringApplication.run(MsUserApp.class, args);
    }
}
