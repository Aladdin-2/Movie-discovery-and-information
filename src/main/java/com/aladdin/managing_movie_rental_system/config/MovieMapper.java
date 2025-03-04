package com.aladdin.managing_movie_rental_system.config;

import com.aladdin.managing_movie_rental_system.dao.entity.Movie;
import com.aladdin.managing_movie_rental_system.model.dto.ResponseMovieDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieMapper {

    public ResponseMovieDto toDto(Movie movie) {
        return new ResponseMovieDto(
                movie.getTitle(),
                movie.getActors(),
                movie.getWriter(),
                movie.getGenre(),
                movie.getLanguage(),
                movie.getDescription(),
                movie.getRuntime(),
                movie.getImdbRating(),
                movie.getWebsite(),
                movie.getLink());
    }

    public List<ResponseMovieDto> toDto(List<Movie> movies) {
        return movies.stream().map(this::toDto).toList();
    }


}
