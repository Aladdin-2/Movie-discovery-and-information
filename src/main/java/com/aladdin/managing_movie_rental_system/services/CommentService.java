package com.aladdin.managing_movie_rental_system.services;

import com.aladdin.managing_movie_rental_system.Exception.ResourceNotFoundException;
import com.aladdin.managing_movie_rental_system.config.CommentMapper;
import com.aladdin.managing_movie_rental_system.dao.entity.Comment;
import com.aladdin.managing_movie_rental_system.dao.entity.Movie;
import com.aladdin.managing_movie_rental_system.dao.entity.User;
import com.aladdin.managing_movie_rental_system.dao.repository.CommentRepository;
import com.aladdin.managing_movie_rental_system.dao.repository.MovieRepository;
import com.aladdin.managing_movie_rental_system.dao.repository.UserRepository;
import com.aladdin.managing_movie_rental_system.model.dto.ResponseCommentDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final CommentMapper commentMapper;
    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    public ResponseCommentDto addComment(Integer userId, Integer movieId, String content) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Movie movie = movieRepository
                .findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setMovie(movie);
        comment.setContent(content);
        Comment save = commentRepository.save(comment);
        return commentMapper.toDto(save);
    }


    public List<ResponseCommentDto> addComments(Integer userId, String content, Integer... movieIds) {
        User user = userRepository.
                findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        List<Integer> ids = Arrays.stream(movieIds).toList();
        List<Movie> allMoviesById = movieRepository.findAllById(ids);
        List<Comment> comments = allMoviesById.stream()
                .map(movie -> new Comment(null, user, movie, content)).toList();
        commentRepository.saveAll(comments);
        logger.info("Comments successfully saved to repository! {}", comments);
        return commentMapper.toDto(comments);
    }

    @Transactional
    public void deleteCommentById(Integer commentId) {
        commentRepository.deleteById(commentId);
        logger.info("Comment successfully deleted!: {}", commentId);
    }

    public void deleteAllComments() {
        commentRepository.deleteAll();
        commentRepository.resetAutoIncrement();
    }
}
