package com.workhub.demo.repository;

import com.workhub.demo.model.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ListRepository extends JpaRepository<ListEntity, UUID> {
    List<ListEntity> findByBoardId(UUID boardId);
}
