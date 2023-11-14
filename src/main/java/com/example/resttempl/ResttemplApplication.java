package com.example.resttempl;

import com.example.resttempl.controller.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class ResttemplApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ResttemplApplication.class, args);
       Controller communication = context.getBean("controller", Controller.class);
       System.out.println("Answer: " + communication.getAnswer());
    }
}