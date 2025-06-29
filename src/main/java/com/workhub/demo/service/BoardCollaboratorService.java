package com.workhub.demo.service;

import com.workhub.demo.model.Board;
import com.workhub.demo.model.BoardCollaborator;
import com.workhub.demo.model.User;
import com.workhub.demo.repository.BoardCollaboratorRepository;
import com.workhub.demo.repository.BoardRepository;
import com.workhub.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BoardCollaboratorService {

    @Autowired
    private BoardCollaboratorRepository boardCollaboratorRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    // 🔹 Use Case 1: Add user as collaborator to board
    public BoardCollaborator addCollaborator(UUID boardId, UUID userId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BoardCollaborator collaborator = new BoardCollaborator();
        collaborator.setBoard(board);
        collaborator.setUser(user);

        return boardCollaboratorRepository.save(collaborator);
    }

    // 🔹 Use Case 2: Get all collaborators of a board
    public List<BoardCollaborator> getCollaboratorsByBoardId(UUID boardId) {
        return boardCollaboratorRepository.findByBoardId(boardId);
    }

    // 🔹 Use Case 3: Get all boards a user collaborates on
    public List<BoardCollaborator> getCollaborationsByUserId(UUID userId) {
        return boardCollaboratorRepository.findByUserId(userId);
    }
}
