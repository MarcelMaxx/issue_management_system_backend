package com.team13.service;

import com.team13.model.Project;
import com.team13.model.User;
import com.team13.repository.ProjectRepository;
import com.team13.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersWithProject() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            if (user.getProjectId() != null) {
                Project project = projectRepository.findById(user.getProjectId()).orElse(null);
                user.setProject(project);
            }
        });
        return users;
    }
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUser(Long userId, User user) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());
            existingUser.setProjectId(user.getProjectId());
            existingUser.setRole(user.getRole());
            return userRepository.save(existingUser);
        }
        return null;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User register(User user) {
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }
}

