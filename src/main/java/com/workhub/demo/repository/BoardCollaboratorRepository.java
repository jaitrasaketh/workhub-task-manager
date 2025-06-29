package com.workhub.demo.repository;

import com.workhub.demo.model.BoardCollaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface BoardCollaboratorRepository extends JpaRepository<BoardCollaborator, UUID> {
    List<BoardCollaborator> findByBoardId(UUID boardId);
    List<BoardCollaborator> findByUserId(UUID userId);
}
