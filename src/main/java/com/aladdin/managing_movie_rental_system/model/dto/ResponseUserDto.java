package com.aladdin.managing_movie_rental_system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDto {
    private String firstName;
    private String lastName;
    private String email;
}
