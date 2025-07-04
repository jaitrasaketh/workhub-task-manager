package com.workhub.demo.repository;

import com.workhub.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByListId(UUID listId);
}
