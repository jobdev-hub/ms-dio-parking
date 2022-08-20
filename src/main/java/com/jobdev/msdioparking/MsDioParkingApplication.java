package com.jobdev.msdioparking;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "ms-parking", version = "1.0.0"))
public class MsDioParkingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsDioParkingApplication.class, args);
    }

}
