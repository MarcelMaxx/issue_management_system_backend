package com.team13.repository;

import com.team13.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByIssueId(Long issueId);

    List<Comment> findByIssueIdOrderByCreatedAtDesc(Long issueId);
}
