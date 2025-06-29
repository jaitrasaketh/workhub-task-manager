package com.workhub.demo.service;

import com.workhub.demo.model.Task;
import com.workhub.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Get a specific task
    public Optional<Task> getTaskById(UUID id) {
        return taskRepository.findById(id);
    }

    // Get all tasks in a list
    public List<Task> getTasksByListId(UUID listId) {
        return taskRepository.findByListId(listId);
    }

    // Save or update a task
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    // Delete a task
    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }
}
