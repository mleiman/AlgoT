package io.github.mleiman.algot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AlgoTApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlgoTApplication.class, args);
    }

}
