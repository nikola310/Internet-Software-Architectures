package com.management.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Zivko Stanisic
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.management")
@EntityScan("com.management.entities")
@EnableJpaRepositories("com.management.repositories")
@EnableTransactionManagement
public class Startup extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Startup.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Startup.class, args);
    }
}
