package com.workhub.demo.service;

import com.workhub.demo.model.Assignment;
import com.workhub.demo.model.Task;
import com.workhub.demo.model.User;
import com.workhub.demo.repository.AssignmentRepository;
import com.workhub.demo.repository.TaskRepository;
import com.workhub.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    // 🔹 Use Case: Assign a user to a task
    public Assignment assignUserToTask(UUID taskId, UUID userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Assignment assignment = new Assignment();
        assignment.setTask(task);
        assignment.setUser(user);
        assignment.setAssignedAt(Instant.now());

        return assignmentRepository.save(assignment);
    }

    // 🔹 Use Case: Get all assignments for a specific task
    public List<Assignment> getAssignmentsByTask(UUID taskId) {
        return assignmentRepository.findByTaskId(taskId);
    }

    // 🔹 Use Case: Get all tasks assigned to a specific user
    public List<Assignment> getAssignmentsByUser(UUID userId) {
        return assignmentRepository.findByUserId(userId);
    }
}
