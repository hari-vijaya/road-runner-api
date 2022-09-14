package com.noobies.roadrunner;

import com.google.maps.GeoApiContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RoadRunnerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoadRunnerApiApplication.class, args);
    }


    @Bean
    public GeoApiContext getContext() {
        return new GeoApiContext.Builder()
                .apiKey("AIzaSyBWNCWizzY1UcYKTG8bzSQxV-gSerIojjg")
                .build();
    }

}
