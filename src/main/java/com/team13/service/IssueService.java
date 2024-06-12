package com.team13.service;

import com.team13.dto.AssignIssueRequest;
import com.team13.dto.UpdateStatusRequest;
import com.team13.model.Comment;
import com.team13.model.Issue;
import com.team13.model.IssueSpecification;
import com.team13.repository.CommentRepository;
import com.team13.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private CommentRepository commentRepository;

    public Issue createIssue(Issue issue) {
        issue.setStatus("new");
        return issueRepository.save(issue);
    }

    public List<Issue> getIssues(String status) {
        if (status != null) {
            return issueRepository.findByStatus(status);
        }
        return issueRepository.findAll();
    }

//    public Issue getIssue(Long issueId) {
//        return issueRepository.findById(issueId).orElse(null);
//    }

    public Issue assignIssue(Long issueId, AssignIssueRequest request) {
        Issue issue = issueRepository.findById(issueId).orElse(null);
        if (issue != null) {
            issue.setAssigneeId(request.getAssigneeId());
            issue.setStatus("assigned");
            issueRepository.save(issue);
            Comment comment = new Comment();
            comment.setIssueId(issueId);
            comment.setCommenterId(request.getAssigneeId());
            comment.setContent(request.getComment());
            comment.setCreatedAt(LocalDateTime.now());
            commentRepository.save(comment);
        }
        return issue;
    }

    public Issue updateIssueStatus(Long issueId, UpdateStatusRequest request) {
        Issue issue = issueRepository.findById(issueId).orElse(null);
        if (issue != null) {
            issue.setStatus(request.getStatus());
            issueRepository.save(issue);
            Comment comment = new Comment();
            comment.setIssueId(issueId);
            comment.setCommenterId(issue.getAssigneeId());
            comment.setContent(request.getComment());
            comment.setCreatedAt(LocalDateTime.now());
            commentRepository.save(comment);
        }
        return issue;
    }

    public Comment addComment(Long issueId, Comment comment) {
        comment.setIssueId(issueId);
        comment.setCreatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    public void deleteIssue(Long issueId) {
        issueRepository.deleteById(issueId);
    }

    public Issue updateIssue(Long issueId, Issue issueDetails) {
        Issue issue = getIssue(issueId);
        if (issue != null) {
            issue.setTitle(issueDetails.getTitle());
            issue.setDescription(issueDetails.getDescription());
            issue.setStatus(issueDetails.getStatus());
            issue.setProjectId(issueDetails.getProjectId());
            issue.setReporterId(issueDetails.getReporterId());
            issue.setAssigneeId(issueDetails.getAssigneeId());
            issue.setPriority(issueDetails.getPriority());
            issue.setFixedId(issueDetails.getFixedId());
            issue.setFixerId(issueDetails.getFixerId());
            return issueRepository.save(issue);
        }
        return null;
    }
    public Issue getIssue(Long issueId) {
        Issue issue = issueRepository.findById(issueId).orElse(null);
        if (issue != null) {
            List<Comment> comments = commentRepository.findByIssueId(issueId);
            issue.setCommentList(comments);
        }
        return issue;
    }
    public List<Issue> getIssuesWithComments(String status) {
        List<Issue> issues;
        if (status != null) {
            issues = issueRepository.findByStatus(status);
        } else {
            issues = issueRepository.findAll();
        }
        issues.forEach(issue -> {
            List<Comment> comments = commentRepository.findByIssueId(issue.getId())
                    .stream()
                    .sorted((c1, c2) -> c2.getCreatedAt().compareTo(c1.getCreatedAt()))
                    .collect(Collectors.toList());
            issue.setCommentList(comments); // Assuming you have a setter for commentList
        });
        return issues;
    }

    public List<Issue> getIssues(String priority, String status, Long userId) {
        Specification<Issue> spec = Specification.where(null);

        if (priority != null && !priority.isEmpty()) {
            spec = spec.and(IssueSpecification.hasPriority(priority));
        }

        if (status != null && !status.isEmpty()) {
            spec = spec.and(IssueSpecification.hasStatus(status));
        }

        if (userId != null) {
            spec = spec.and(IssueSpecification.hasUserId(userId));
        }
        List<Issue> issues = issueRepository.findAll(spec);
        issues.forEach(issue -> {
            List<Comment> comments = commentRepository.findByIssueId(issue.getId())
                    .stream()
                    .sorted((c1, c2) -> c2.getCreatedAt().compareTo(c1.getCreatedAt()))
                    .collect(Collectors.toList());
            issue.setCommentList(comments); // Assuming you have a setter for commentList
        });

        return issues;
    }

    public List<Comment> getCommentsByIssueId(Long issueId) {
        return commentRepository.findByIssueIdOrderByCreatedAtDesc(issueId);
    }

    public Issue updateIssueStatus(Long issueId, String status) {
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new RuntimeException("Issue not found"));
        issue.setStatus(status);
        return issueRepository.save(issue);
    }

}

