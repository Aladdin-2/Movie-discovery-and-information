package com.aladdin.managing_movie_rental_system.config;

import com.aladdin.managing_movie_rental_system.dao.entity.Comment;
import com.aladdin.managing_movie_rental_system.model.dto.ResponseCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    private final UserMapper userMapper;
    private final MovieMapper movieMapper;

    public ResponseCommentDto toDto(Comment comment) {
        return new ResponseCommentDto(
                userMapper.toDto(comment.getUser()),
                movieMapper.toDto(comment.getMovie()),
                comment.getContent()
        );
    }

    public List<ResponseCommentDto> toDto(List<Comment> comments) {
        return comments.stream().map(this::toDto).toList();
    }
}
