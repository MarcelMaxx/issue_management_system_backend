package com.team13.dto;

import java.util.Scanner;

public class AssignIssueRequest {
    private Long assigneeId;
    private String comment;
    // getters and setters


    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}