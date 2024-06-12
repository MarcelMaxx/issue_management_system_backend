package com.team13.controller;

import com.team13.model.Comment;
import com.team13.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> getComments() {
        List<Comment> comments = commentService.getComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable Long commentId) {
        Comment comment = commentService.getComment(commentId);
        return ResponseEntity.ok(comment);
    }

//    @PreAuthorize("hasAnyRole('DEV', 'PL', 'TESTER')")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/issue/{issueId}")
    public ResponseEntity<List<Comment>> getCommentsByIssueId(@PathVariable Long issueId) {
        List<Comment> comments = commentService.getCommentsByIssueId(issueId);
        return ResponseEntity.ok(comments);
    }
}

