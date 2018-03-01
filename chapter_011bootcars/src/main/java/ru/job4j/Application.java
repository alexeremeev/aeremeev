package ru.job4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.job4j"})
@EnableJpaRepositories(basePackages = "ru.job4j")
@EnableTransactionManagement
@EntityScan(basePackages = "ru.job4j")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
