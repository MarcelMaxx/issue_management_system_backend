package com.team13.service;

import com.team13.model.Comment;
import com.team13.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<Comment> getCommentsByIssueId(Long issueId) {
        return commentRepository.findByIssueId(issueId);
    }
}

