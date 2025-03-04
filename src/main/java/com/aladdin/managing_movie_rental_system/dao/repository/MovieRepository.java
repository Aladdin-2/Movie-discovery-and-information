package com.aladdin.managing_movie_rental_system.dao.repository;

import com.aladdin.managing_movie_rental_system.dao.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findByTitle(String name);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE movies AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
