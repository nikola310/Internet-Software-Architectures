package com.management.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author Zivko Stanisic
 *
 */
@SpringBootApplication
public class Startup extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Startup.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Startup.class, args);
    }
}
