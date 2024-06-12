package com.team13.controller;

import com.team13.dto.AssignIssueRequest;
import com.team13.dto.UpdateStatusRequest;
import com.team13.model.Comment;
import com.team13.model.Issue;
import com.team13.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

//    @PreAuthorize("hasRole('TESTER')")
    @PostMapping
    public ResponseEntity<Issue> createIssue(@RequestBody Issue issue) {
        Issue createdIssue = issueService.createIssue(issue);
        return ResponseEntity.ok(createdIssue);
    }

//    @GetMapping
//    public ResponseEntity<List<Issue>> getIssuesWithComments(@RequestParam(required = false) String status) {
//        List<Issue> issuesWithComments = issueService.getIssuesWithComments(status);
//        return ResponseEntity.ok(issuesWithComments);
//    }

    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssue(@PathVariable Long issueId) {
        Issue issue = issueService.getIssue(issueId);
        return ResponseEntity.ok(issue);
    }

//    @PreAuthorize("hasRole('PL')")
    @PutMapping("/{issueId}/assign")
    public ResponseEntity<Issue> assignIssue(@PathVariable Long issueId, @RequestBody AssignIssueRequest request) {
        Issue assignedIssue = issueService.assignIssue(issueId, request);
        return ResponseEntity.ok(assignedIssue);
    }

//    @PreAuthorize("hasAnyRole('DEV', 'TESTER')")
    @PutMapping("/{issueId}/status")
    public ResponseEntity<Issue> updateIssueStatus(@PathVariable Long issueId, @RequestBody UpdateStatusRequest request) {
        Issue updatedIssue = issueService.updateIssueStatus(issueId, request);
        return ResponseEntity.ok(updatedIssue);
    }

    @PostMapping("/{issueId}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long issueId, @RequestBody Comment comment) {
        Comment addedComment = issueService.addComment(issueId, comment);
        return ResponseEntity.ok(addedComment);
    }

//    @PreAuthorize("hasRole('TESTER')")
    @DeleteMapping("/{issueId}")
    public ResponseEntity<Void> deleteIssue(@PathVariable Long issueId) {
        issueService.deleteIssue(issueId);
        return ResponseEntity.noContent().build();
    }
    // 新的修改接口
    @PutMapping("/{issueId}")
    public ResponseEntity<Issue> updateIssue(@PathVariable Long issueId, @RequestBody Issue issue) {
        Issue updatedIssue = issueService.updateIssue(issueId, issue);
        return ResponseEntity.ok(updatedIssue);
    }

    @GetMapping
    public List<Issue> getIssues(@RequestParam(required = false) String priority,
                                 @RequestParam(required = false) String status,
                                 @RequestParam(required = false) Long userId) {
        return issueService.getIssues(priority, status, userId);
    }

    @GetMapping("/{issueId}/comments")
    public List<Comment> getCommentsByIssueId(@PathVariable Long issueId) {
        return issueService.getCommentsByIssueId(issueId);
    }

    @PutMapping("/{issueId}/{status}")
    public ResponseEntity<Issue> updateIssueStatus(@PathVariable Long issueId, @PathVariable String status) {
        Issue updatedIssue = issueService.updateIssueStatus(issueId, status);
        return ResponseEntity.ok(updatedIssue);
    }

}

