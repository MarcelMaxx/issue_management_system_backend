package com.team13.service;

import com.team13.model.Issue;
import com.team13.model.Project;
import com.team13.repository.IssueRepository;
import com.team13.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private IssueRepository issueRepository;

    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

//    public List<Project> getProjects() {
//        return projectRepository.findAll();
//    }

    public Project getProject(Long projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }

    public Project updateProject(Long projectId, Project project) {
        Project existingProject = projectRepository.findById(projectId).orElse(null);
        if (existingProject != null) {
            existingProject.setName(project.getName());
            existingProject.setDescription(project.getDescription());
            return projectRepository.save(existingProject);
        }
        return null;
    }

    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    public List<Project> getProjects() {
        List<Project> projects = projectRepository.findAll();
        for (Project project : projects) {
            List<Issue> issues = issueRepository.findByProjectId(project.getId());
            project.setIssueList(issues);
        }
        return projects;
    }
}

