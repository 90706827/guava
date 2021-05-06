package com.chanjet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * niho
 *
 * @author jimmy
 */
@SpringBootApplication
public class GuavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuavaApplication.class, args);
        List<String> list = new ArrayList<>();
    }
}
