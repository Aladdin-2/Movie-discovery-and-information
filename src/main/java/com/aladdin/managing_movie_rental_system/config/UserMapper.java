package com.aladdin.managing_movie_rental_system.config;

import com.aladdin.managing_movie_rental_system.dao.entity.User;
import com.aladdin.managing_movie_rental_system.model.dto.ResponseUserDto;
import com.aladdin.managing_movie_rental_system.model.dto.ResponseUserWithMovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final MovieMapper movieMapper;

    public ResponseUserDto toDto(User user) {
        return new ResponseUserDto(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

    public ResponseUserWithMovieDto toDtoWithMovie(User user) {
        return new ResponseUserWithMovieDto(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getMovies()
        );
    }

    public List<ResponseUserDto> toDto(List<User> users) {
        return users.stream().map(this::toDto).toList();
    }

    public List<ResponseUserWithMovieDto> toDtoWithMovie(List<User> users) {
        return users.stream().map(this::toDtoWithMovie).toList();
    }


}
