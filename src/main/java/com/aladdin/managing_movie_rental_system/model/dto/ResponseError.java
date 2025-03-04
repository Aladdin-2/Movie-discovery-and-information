package com.aladdin.managing_movie_rental_system.model.dto;

public record ResponseError(int status,
                            String message,
                            String details,
                            String errorTime
) {
}
