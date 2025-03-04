package com.aladdin.managing_movie_rental_system.model.dto;

import com.aladdin.managing_movie_rental_system.dao.entity.Movie;
import com.aladdin.managing_movie_rental_system.dao.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCommentDto {
    private ResponseUserDto user;
    private ResponseMovieDto movie;
    private String content;
}
