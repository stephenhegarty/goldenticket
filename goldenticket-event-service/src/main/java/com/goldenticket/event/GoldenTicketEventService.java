package com.goldenticket.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class GoldenTicketEventService {
    public static void main(String[] args) {
        SpringApplication.run(GoldenTicketEventService.class);
    }
}