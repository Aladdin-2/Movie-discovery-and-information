package com.aladdin.managing_movie_rental_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ManagingAMovieRentalSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagingAMovieRentalSystemApplication.class, args);
    }

}
