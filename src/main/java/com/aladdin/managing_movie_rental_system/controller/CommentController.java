package com.aladdin.managing_movie_rental_system.controller;

import com.aladdin.managing_movie_rental_system.model.dto.ResponseCommentDto;
import com.aladdin.managing_movie_rental_system.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/aladdin.com/movies_site/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{movieId}/comment/{userId}")
    public ResponseCommentDto addComment(@PathVariable Integer movieId,
                                         @PathVariable Integer userId,
                                         @RequestParam String content) {
        return commentService.addComment(userId, movieId, content);
    }

    @PostMapping(path = "/{userId}/userId/comment/{movieIds}")
    public List<ResponseCommentDto> addComments(@PathVariable(name = "userId") Integer userId,
                                                @RequestParam(name = "content") String content,
                                                @PathVariable(name = "movieIds") Integer... movieIds) {
        return commentService.addComments(userId, content, movieIds);
    }

    @DeleteMapping(path = "/delete/{commentId}")
    public void deleteCommentById(@PathVariable(name = "commentId") Integer commentId) {
        commentService.deleteCommentById(commentId);
    }

    @DeleteMapping(path = "/all/comments")
    public void deleteAllComments(){
        commentService.deleteAllComments();
    }
}
