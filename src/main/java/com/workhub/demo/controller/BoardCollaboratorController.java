package com.workhub.demo.controller;

import com.workhub.demo.model.BoardCollaborator;
import com.workhub.demo.service.BoardCollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class BoardCollaboratorController {

    private final BoardCollaboratorService boardCollaboratorService;

    @Autowired
    public BoardCollaboratorController(BoardCollaboratorService boardCollaboratorService) {
        this.boardCollaboratorService = boardCollaboratorService;
    }

    // ✅ Add a user as a collaborator to a board
    @PostMapping("/boards/{boardId}/collaborators")
    public ResponseEntity<BoardCollaborator> addCollaborator(
            @PathVariable UUID boardId,
            @RequestParam UUID userId
    ) {
        BoardCollaborator added = boardCollaboratorService.addCollaborator(boardId, userId);
        return ResponseEntity.ok(added);
    }

    // ✅ Get all collaborators for a board
    @GetMapping("/boards/{boardId}/collaborators")
    public ResponseEntity<List<BoardCollaborator>> getCollaborators(
            @PathVariable UUID boardId
    ) {
        List<BoardCollaborator> collaborators = boardCollaboratorService.getCollaboratorsByBoardId(boardId);
        return ResponseEntity.ok(collaborators);
    }

    // ✅ Get all boards a user collaborates on
    @GetMapping("/users/{userId}/boards")
    public ResponseEntity<List<BoardCollaborator>> getBoardsForUser(
            @PathVariable UUID userId
    ) {
        List<BoardCollaborator> boards = boardCollaboratorService.getCollaborationsByUserId(userId);
        return ResponseEntity.ok(boards);
    }
}
