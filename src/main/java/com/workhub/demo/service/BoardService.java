package com.workhub.demo.service;

import com.workhub.demo.model.Board;
import com.workhub.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
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
        return boardRepository.save(board);
    }

    // Delete board by ID
    public void deleteBoard(UUID id) {
        boardRepository.deleteById(id);
    }
}
