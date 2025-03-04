package com.aladdin.managing_movie_rental_system.services;

import com.aladdin.managing_movie_rental_system.Exception.ResourceAlreadyExistsException;
import com.aladdin.managing_movie_rental_system.Exception.ResourceNotFoundException;
import com.aladdin.managing_movie_rental_system.config.UserMapper;
import com.aladdin.managing_movie_rental_system.dao.entity.Movie;
import com.aladdin.managing_movie_rental_system.dao.entity.User;
import com.aladdin.managing_movie_rental_system.dao.repository.CommentRepository;
import com.aladdin.managing_movie_rental_system.dao.repository.MovieRepository;
import com.aladdin.managing_movie_rental_system.dao.repository.UserRepository;
import com.aladdin.managing_movie_rental_system.model.dto.ResponseUserDto;
import com.aladdin.managing_movie_rental_system.model.dto.ResponseUserWithMovieDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final UserMapper userMapper;
    private final Logger logger;
    private final CommentRepository commentRepository;

    private Integer lastUserId;

    public ResponseUserDto saveUser(User user) {
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public ResponseUserDto getUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Not found user with this id: " + id));
        return userMapper.toDto(user);
    }

    @Transactional
    public ResponseUserWithMovieDto getUserWithMovies(Integer id) {
        User user = userRepository.
                findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found user with this id: " + id));
        return userMapper.toDtoWithMovie(user);
    }

    public List<ResponseUserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDto(users);
    }

    public List<ResponseUserWithMovieDto> getAllUsersWithMovies() {
        List<User> allUsersWithMovies = userRepository.findAll();
        return userMapper.toDtoWithMovie(allUsersWithMovies);
    }

    @Transactional
    public ResponseUserWithMovieDto addMovieToUser(Integer userId, Integer movieId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found!"));

        boolean movieAlreadyExists = user.getMovies().stream()
                .anyMatch(movie1 -> movie1.getId().equals(movieId));
        if (movieAlreadyExists) {
            throw new ResourceAlreadyExistsException("This movie is already available for this user!");
        }
        user.getMovies().add(movie);
        userRepository.save(user);
        this.lastUserId = userId;
        return userMapper.toDtoWithMovie(user);
    }

    public ResponseUserDto updateUser(Integer id, User user) {
        if (id != null && user != null) {
            User findingUser = userRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Not found user with this id: " + id));
            findingUser.setFirstName(user.getFirstName());
            findingUser.setLastName(user.getLastName());
            findingUser.setEmail(user.getEmail());
            userRepository.save(findingUser);
            return userMapper.toDto(user);
        }
        throw new IllegalArgumentException("ID or user incorrect!");
    }

    @Transactional
    public void deleteUserById(Integer userId) {
        if (userId != null && userRepository.existsById(userId)) {
            commentRepository.deleteByUserId(userId);
            userRepository.deleteById(userId);
        } else {
            throw new ResourceNotFoundException("Not found with this id: " + userId);
        }
    }

    @Transactional
    public void deleteUsersMovie(Integer id, Integer... moviesId) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Not found user with this id: " + id));

        if (user.getMovies().isEmpty()) {
            throw new ResourceNotFoundException("This user ID: " + id + " has no movies.");
        }
        List<Integer> list = Arrays.stream(moviesId).toList();
        List<Movie> movies = new ArrayList<>(user.getMovies());
        movies.removeIf(movie -> list.contains(movie.getId()));

        if (movies.size() == user.getMovies().size()) {
            throw new ResourceNotFoundException("Some or all specified movies were not found for the user.");
        }
        user.setMovies(movies);
        userRepository.save(user);
    }

    //@Scheduled(fixedRate = 30000)
    @Transactional
    public void deleteUsersMovies() {
        if (lastUserId != null) {
            User user = userRepository.findById(lastUserId).orElseThrow(() ->
                    new ResourceNotFoundException("Not found user with this id: " + lastUserId));
            if (!user.getMovies().isEmpty()) {
                Movie lastMovie = user.getMovies().get(user.getMovies().size() - 1);
                user.getMovies().remove(lastMovie);
                userRepository.save(user);
                logger.info("This user's movies have been deleted {}", lastUserId);
            } else {
                logger.info("This user no longer has any movies.{}", lastUserId);
            }
        } else {
            logger.info("No user IDs saved.");
        }
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
        userRepository.resetAutoIncrement();
    }
}
