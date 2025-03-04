package com.aladdin.managing_movie_rental_system.Exception;

import com.aladdin.managing_movie_rental_system.model.dto.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalResponseExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseError> responseHandler(RuntimeException ex, WebRequest request) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
        String formattedDate = now.format(formatter);
        ResponseError errorResponse = new ResponseError(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false),
                formattedDate
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}