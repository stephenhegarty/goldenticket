package com.goldenticket.event.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.goldenticket.event.persistence")
@EntityScan(basePackages = "com.goldenticket.event.persistence")
public class GoldenTicketEventConfig {
}