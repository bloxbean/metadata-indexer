package com.bloxbean.cardano.metdataindexer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class MetdataIndexerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetdataIndexerApplication.class, args);
    }

}
