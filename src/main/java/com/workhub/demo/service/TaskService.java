package com.workhub.demo.service;

import com.workhub.demo.model.ListEntity;
import com.workhub.demo.model.Task;
import com.workhub.demo.repository.ListRepository;
import com.workhub.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

    @Autowired
    private ListRepository listRepository;

    // Save or update a task
    public Task saveTask(Task task) {
        // ✅ Defensive null checks
        if (task.getList() == null || task.getList().getId() == null) {
            throw new IllegalArgumentException("List ID must be provided in the task payload.");
        }

        // ✅ Resolve full ListEntity from DB
        UUID listId = task.getList().getId();
        ListEntity list = listRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("List not found with ID: " + listId));

        task.setList(list);

        // ✅ Set default timestamps if needed (optional)
        if (task.getCreatedAt() == null) {
            task.setCreatedAt(Instant.now());
        }

        return taskRepository.save(task);
    }


    // Delete a task
    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }
}
