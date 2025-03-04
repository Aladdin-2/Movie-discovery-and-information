package com.aladdin.managing_movie_rental_system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMovieDto {
    private String title;
    private String actors;
    private String writer;
    private String genre;
    private String language;
    private String description;
    private String runtime;
    private String imdbRating;
    private String site;
    private String link;

}
