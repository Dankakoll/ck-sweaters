package com.sweaters.demo1;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.slf4j.Logger;
@Configuration
class LoadDatabase {
    public static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase (ItemsRepository repository)
    {
        return args -> {
            log.info("Preloading " + repository.save(new Items("first name", 500L)));
            log.info("Preloading " + repository.save(new Items("first name", 1000L)));
            log.info("Preloading " + repository.save(new Items("second name", 1500L)));
            log.info("Preloading " + repository.save(new Items("second name", 3000L)));
        };
    }
}
