package com.workhub.demo.controller;

import com.workhub.demo.model.ListEntity;
import com.workhub.demo.model.Task;
import com.workhub.demo.service.ListService;
import com.workhub.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ListService listService;

    @Autowired
    public TaskController(TaskService taskService, ListService listService) {
        this.taskService = taskService;
        this.listService = listService;
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

    // ✅ NEW: Update a task (partial)
    @PatchMapping("/{id}")
    public Task updateTask(@PathVariable UUID id, @RequestBody Task updatedTask) {
        Optional<Task> existingOpt = taskService.getTaskById(id);
        if (existingOpt.isEmpty()) return null;

        Task existing = existingOpt.get();

        if (updatedTask.getTitle() != null) existing.setTitle(updatedTask.getTitle());
        if (updatedTask.getDescription() != null) existing.setDescription(updatedTask.getDescription());
        if (updatedTask.getDueDate() != null) existing.setDueDate(updatedTask.getDueDate());
        if (updatedTask.getPriority() != null) existing.setPriority(updatedTask.getPriority());
        if (updatedTask.getStatus() != null) existing.setStatus(updatedTask.getStatus());

        return taskService.saveTask(existing);
    }

    // ✅ NEW: Delete task
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
    }

    // ✅ NEW: Move task to another list
    @PatchMapping("/{taskId}/move")
    public Task moveTask(@PathVariable UUID taskId, @RequestParam UUID targetListId) {
        Optional<Task> taskOpt = taskService.getTaskById(taskId);
        Optional<ListEntity> listOpt = listService.getListById(targetListId);

        if (taskOpt.isEmpty() || listOpt.isEmpty()) return null;

        Task task = taskOpt.get();
        task.setList(listOpt.get());

        return taskService.saveTask(task);
    }
}
