package com.aladdin.managing_movie_rental_system.model.dto;

import com.aladdin.managing_movie_rental_system.dao.entity.Comment;
import com.aladdin.managing_movie_rental_system.dao.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserWithMovieDto {

    private String firstName;
    private String lastName;
    private String email;
    private List<Movie> movies;
}
