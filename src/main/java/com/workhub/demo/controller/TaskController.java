package com.workhub.demo.controller;

import com.workhub.demo.model.Task;
import com.workhub.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Create a task
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    // Get all tasks in a list
    @GetMapping("/list/{listId}")
    public List<Task> getTasksByListId(@PathVariable UUID listId) {
        return taskService.getTasksByListId(listId);
    }
}
