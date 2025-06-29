package com.workhub.demo.service;

import com.workhub.demo.model.Board;
import com.workhub.demo.model.User;
import com.workhub.demo.repository.BoardRepository;
import com.workhub.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    // Get board by ID
    public Optional<Board> getBoardById(UUID id) {
        return boardRepository.findById(id);
    }

    // Get all boards owned by a user
    public List<Board> getBoardsByOwnerId(UUID ownerId) {
        return boardRepository.findByOwnerId(ownerId);
    }

    // Create or update a board
    public Board saveBoard(Board board) {
        if (board.getId() == null) {
            board.setCreatedAt(Instant.now()); // set only on creation
        }
        board.setUpdatedAt(Instant.now()); // always update on save

        // resolve full owner entity
        UUID ownerId = board.getOwner().getId();
        User fullOwner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("User not found: " + ownerId));
        board.setOwner(fullOwner);

        return boardRepository.save(board);
    }

    // Delete board by ID
    public void deleteBoard(UUID id) {
        boardRepository.deleteById(id);
    }
}
