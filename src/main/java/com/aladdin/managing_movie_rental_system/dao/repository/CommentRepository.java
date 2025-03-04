package com.aladdin.managing_movie_rental_system.dao.repository;

import com.aladdin.managing_movie_rental_system.dao.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE comment AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

    void deleteByMovieId(Integer movieId);

    void deleteByUserId(Integer userId);
}
