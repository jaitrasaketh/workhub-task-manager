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

    private final AssignmentRepository assignmentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository,
                             TaskRepository taskRepository,
                             UserRepository userRepository) {
        this.assignmentRepository = assignmentRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    // 🔹 Use Case: Assign a user to a task
    public Assignment assignUserToTask(UUID taskId, UUID userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ❗ Check if task is already assigned
        if (task.getAssignment() != null) {
            // Avoid 500 error — just return existing assignment
            return task.getAssignment();
        }

        // ✅ Create new assignment
        Assignment assignment = new Assignment();
        assignment.setTask(task);
        assignment.setUser(user);
        assignment.setAssignedAt(Instant.now());

        // Bi-directional mapping (optional but safer)
        task.setAssignment(assignment);

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
