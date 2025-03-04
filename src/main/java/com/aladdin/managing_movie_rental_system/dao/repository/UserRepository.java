package com.aladdin.managing_movie_rental_system.dao.repository;

import com.aladdin.managing_movie_rental_system.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE users AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

}
