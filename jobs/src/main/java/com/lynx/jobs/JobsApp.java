package com.lynx.jobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JobsApp {
    public static void main(String[] args) {
        SpringApplication.run(JobsApp.class, args);
    }
}
