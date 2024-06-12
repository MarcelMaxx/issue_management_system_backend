package com.team13.controller;

import com.team13.model.Project;
import com.team13.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    //    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        Project createdProject = projectService.addProject(project);
        return ResponseEntity.ok(createdProject);
    }

    //    @GetMapping
//    public ResponseEntity<List<Project>> getProjects() {
//        List<Project> projects = projectService.getProjects();
//        return ResponseEntity.ok(projects);
//    }
    @GetMapping
    public ResponseEntity<List<Project>> getProjects() {
        List<Project> projects = projectService.getProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProject(@PathVariable Long projectId) {
        Project project = projectService.getProject(projectId);
        return ResponseEntity.ok(project);
    }

    //    @PreAuthorize("hasRole('PL')")
    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable Long projectId, @RequestBody Project project) {
        Project updatedProject = projectService.updateProject(projectId, project);
        return ResponseEntity.ok(updatedProject);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }
}


