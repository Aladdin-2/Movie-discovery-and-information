package com.aladdin.managing_movie_rental_system.services;

import com.aladdin.managing_movie_rental_system.Exception.ResourceAlreadyExistsException;
import com.aladdin.managing_movie_rental_system.Exception.ResourceNotFoundException;
import com.aladdin.managing_movie_rental_system.config.MovieMapper;
import com.aladdin.managing_movie_rental_system.dao.entity.Movie;
import com.aladdin.managing_movie_rental_system.dao.repository.CommentRepository;
import com.aladdin.managing_movie_rental_system.dao.repository.MovieRepository;
import com.aladdin.managing_movie_rental_system.model.dto.ResponseMovieDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final Logger logger;
    private final CommentRepository commentRepository;

    @Value("${API.key}")
    private String API_KEY;

    public ResponseMovieDto saveMovie(Movie movie) {
        movieRepository.save(movie);
        return movieMapper.toDto(movie);
    }

    public ResponseMovieDto saveMovieFromRemoteSite(String title) throws JsonProcessingException {
        Movie omdbMovie = getOmdbUrl(title);
        if (omdbMovie.getTitle() == null) {
            throw new ResourceNotFoundException("We couldn't find a movie with this name! -> " + title);
        }
        ResponseMovieDto response = saveIfNotExists(omdbMovie, title);
        if (response != null) {
            return response;
        }
        throw new ResourceNotFoundException("We couldn't find a movie with this name! -> " + title);
    }

    public Movie getOmdbUrl(String title) throws JsonProcessingException {
        String encodedMovieName = URLEncoder.encode(title, StandardCharsets.UTF_8);
        String url = "https://www.omdbapi.com/?t=" + encodedMovieName + API_KEY;
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String responseBody = responseEntity.getBody();
        System.out.println(responseBody);
        return objectMapper.readValue(responseBody, Movie.class);
    }

    private ResponseMovieDto saveIfNotExists(Movie movie, String title) {
        if (!isMovieExists(title)) {
            movieRepository.save(movie);
            return movieMapper.toDto(movie);
        } else {
            throw new ResourceAlreadyExistsException("This movie is already registered! -> " + title);
        }
    }

    public boolean isMovieExists(String title) {
        return movieRepository.findByTitle(title) != null;
    }

    public List<ResponseMovieDto> getAllMovies() {
        return movieMapper.toDto(movieRepository.findAll());
    }

    public ResponseMovieDto getByName(String name) {
        Movie movie = movieRepository.findByTitle(name);
        return movieMapper.toDto(movie);
    }

    @Transactional
    public void deleteMovieById(Integer movieId) {
        commentRepository.deleteByMovieId(movieId);
        movieRepository.deleteById(movieId);
        logger.info("User successfully deleted! {}", movieId);
    }


    public void deleteAllMovies() {
        commentRepository.deleteAll();
        movieRepository.deleteAll();
        movieRepository.resetAutoIncrement();
    }
}