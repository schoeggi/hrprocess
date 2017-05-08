package ch.fhnw.recruiting;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableProcessApplication
public class HRprocessApplication {

    private static final Logger logger = LoggerFactory.getLogger(HRprocessApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HRprocessApplication.class, args);
    }

}
