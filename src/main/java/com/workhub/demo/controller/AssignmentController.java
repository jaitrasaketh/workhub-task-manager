package com.workhub.demo.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workhub.demo.model.Assignment;
import com.workhub.demo.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    @Autowired
    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    // 🔹 Assign a user to a task
    @PostMapping
    public Assignment assignUser(@RequestBody AssignRequest request) {
        return assignmentService.assignUserToTask(request.getTaskId(), request.getUserId());
    }

    // 🔹 Get all users assigned to a task
    @GetMapping("/task/{taskId}")
    public List<Assignment> getAssignmentsByTask(@PathVariable UUID taskId) {
        return assignmentService.getAssignmentsByTask(taskId);
    }

    // 🔹 Get all tasks assigned to a user
    @GetMapping("/user/{userId}")
    public List<Assignment> getAssignmentsByUser(@PathVariable UUID userId) {
        return assignmentService.getAssignmentsByUser(userId);
    }

    // 🔹 Request DTO
    public static class AssignRequest {
        @JsonProperty("taskId")
        private UUID taskId;

        @JsonProperty("userId")
        private UUID userId;

        public UUID getTaskId() {
            return taskId;
        }

        public void setTaskId(UUID taskId) {
            this.taskId = taskId;
        }

        public UUID getUserId() {
            return userId;
        }

        public void setUserId(UUID userId) {
            this.userId = userId;
        }
    }
}
