package com.aladdin.managing_movie_rental_system.controller;

import com.aladdin.managing_movie_rental_system.dao.entity.User;
import com.aladdin.managing_movie_rental_system.model.dto.ResponseUserDto;
import com.aladdin.managing_movie_rental_system.model.dto.ResponseUserWithMovieDto;
import com.aladdin.managing_movie_rental_system.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/aladdin.com/movies_site/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/user")
    public ResponseUserDto saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping(path = "user/{userId}/movie/{movieId}")
    public ResponseUserWithMovieDto addMovieToUser(@PathVariable(name = "userId") Integer userId,
                                                   @PathVariable(name = "movieId") Integer movieId) {
        return userService.addMovieToUser(userId, movieId);
    }

    @GetMapping(path = "user/{id}")
    public ResponseUserDto getUser(@PathVariable(name = "id") Integer id) {
        return userService.getUser(id);
    }

    @GetMapping(path = "userSMovie/{id}")
    public ResponseUserWithMovieDto getUserWithMovies(@PathVariable(name = "id") Integer id) {
        return userService.getUserWithMovies(id);
    }

    @GetMapping(path = "users")
    public List<ResponseUserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @Transactional
    @GetMapping(path = "usersWithMovies")
    public List<ResponseUserWithMovieDto> getAllUsersWithMovies() {
        return userService.getAllUsersWithMovies();
    }

    @PutMapping(path = "userId/{id}")
    public ResponseUserDto updateUser(@PathVariable(name = "id") Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUserById(@PathVariable(name = "id") Integer id) {
        userService.deleteUserById(id);
    }

    @DeleteMapping(path = "/id/{id}/{moviesID}/movieId")
    public void deleteUsersMovie(@PathVariable(name = "id") Integer id,
                                 @PathVariable(name = "moviesID") Integer... moviesId) {
        userService.deleteUsersMovie(id, moviesId);
    }

    @DeleteMapping(path = "all")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }
}