package com.everyday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// 개발
//@SpringBootApplication
//public class EverydayApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(EverydayApplication.class, args);
//    }
//
//}

// 배포
@SpringBootApplication
public class EverydayApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EverydayApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(EverydayApplication.class, args);
    }
}
