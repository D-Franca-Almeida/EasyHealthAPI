package com.fatec.easyhealthapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.fatec.easyhealthapi")
public class EasyhealthapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyhealthapiApplication.class, args);
    }

}
