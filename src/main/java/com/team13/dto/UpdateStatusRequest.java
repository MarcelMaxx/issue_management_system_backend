package com.team13.dto;

import java.util.Scanner;

public class UpdateStatusRequest {
    private String status;
    private String comment;
    // getters and setters

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}