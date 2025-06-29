package com.workhub.demo.controller;

import com.workhub.demo.model.Board;
import com.workhub.demo.model.ListEntity;
import com.workhub.demo.service.BoardService;
import com.workhub.demo.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;
    private final ListService listService;

    @Autowired
    public BoardController(BoardService boardService, ListService listService) {
        this.boardService = boardService;
        this.listService = listService;
    }

    // Create a new board
    @PostMapping
    public Board createBoard(@RequestBody Board board) {
        return boardService.saveBoard(board);
    }

    // Get a board by ID
    @GetMapping("/{id}")
    public Board getBoardById(@PathVariable UUID id) {
        return boardService.getBoardById(id).orElse(null);
    }

    // Get all boards owned by a user
    @GetMapping("/user/{userId}")
    public List<Board> getBoardsByOwner(@PathVariable UUID userId) {
        return boardService.getBoardsByOwnerId(userId);
    }

    // ✅ NEW: Get all lists in a board
    @GetMapping("/{boardId}/lists")
    public List<ListEntity> getListsByBoard(@PathVariable UUID boardId) {
        return listService.getListsByBoardId(boardId);
    }
}
