package com.workhub.demo.repository;

import com.workhub.demo.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface AssignmentRepository extends JpaRepository<Assignment, UUID> {
    List<Assignment> findByTaskId(UUID taskId);
    List<Assignment> findByUserId(UUID userId);
}
