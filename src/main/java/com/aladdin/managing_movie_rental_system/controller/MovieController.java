package com.aladdin.managing_movie_rental_system.controller;

import com.aladdin.managing_movie_rental_system.dao.entity.Movie;
import com.aladdin.managing_movie_rental_system.model.dto.ResponseMovieDto;
import com.aladdin.managing_movie_rental_system.services.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aladdin.com/movies_site/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/save-move")
    public ResponseMovieDto saveMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @GetMapping("/movie/{title}")
    public ResponseMovieDto getMovieLink(@PathVariable(name = "title") String title) {
        return movieService.getByName(title);
    }

    @PostMapping(path = "/save/from-omdb/{title}")
    public ResponseMovieDto saveMovieOMDB(@PathVariable(name = "title") String movieName) throws JsonProcessingException {
        return movieService.saveMovieFromRemoteSite(movieName);
    }

    @GetMapping(path = "/all")
    public List<ResponseMovieDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    @DeleteMapping(path = "/delete/{movieId}")
    public void deleteMovieById(@PathVariable(name = "movieId") Integer movieId) {
        movieService.deleteMovieById(movieId);
    }

    @DeleteMapping(path = "/delete/All")
    public void deleteAllMovies() {
        movieService.deleteAllMovies();
    }

}
