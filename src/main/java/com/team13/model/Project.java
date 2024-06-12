package com.team13.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    private LocalDate  start_date;
    private LocalDate  end_date;
    @Transient
    private List<Issue> issueList;

    public List<Issue> getIssueList() {
        return issueList;
    }

    public void setIssueList(List<Issue> issueList) {
        this.issueList = issueList;
    }

    // getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getStart_date() {
        return start_date;
    }
    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }
    public LocalDate getEnd_date() {
        return end_date;
    }
    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }
}
