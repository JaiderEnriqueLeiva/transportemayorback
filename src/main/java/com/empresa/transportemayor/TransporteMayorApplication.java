package com.empresa.transportemayor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TransporteMayorApplication {

  public static void main(String[] args) {
    SpringApplication.run(TransporteMayorApplication.class, args);
  }

}
