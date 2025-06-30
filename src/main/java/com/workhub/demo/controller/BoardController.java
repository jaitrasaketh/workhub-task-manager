package com.workhub.demo.controller;

import com.workhub.demo.model.Board;
import com.workhub.demo.model.ListEntity;
import com.workhub.demo.service.BoardService;
import com.workhub.demo.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    // ✅ Create a new board
    @PostMapping
    public Board createBoard(@RequestBody Board board) {
        return boardService.saveBoard(board);
    }

    // ✅ Get board by ID
    @GetMapping("/{id}")
    public Board getBoardById(@PathVariable UUID id) {
        return boardService.getBoardById(id).orElse(null);
    }

    // ✅ Get all boards owned by a user
    @GetMapping("/user/{userId}")
    public List<Board> getBoardsByOwner(@PathVariable UUID userId) {
        return boardService.getBoardsByOwnerId(userId);
    }

    // ✅ Get all lists inside a board
    @GetMapping("/{boardId}/lists")
    public List<ListEntity> getListsByBoard(@PathVariable UUID boardId) {
        return listService.getListsByBoardId(boardId);
    }

    // ✅ Update board name
    @PatchMapping("/{id}")
    public Board updateBoard(@PathVariable UUID id, @RequestBody Board updatedBoard) {
        Optional<Board> boardOpt = boardService.getBoardById(id);
        if (boardOpt.isEmpty()) {
            return null;
        }

        Board board = boardOpt.get();
        if (updatedBoard.getName() != null) {
            board.setName(updatedBoard.getName());
        }

        return boardService.saveBoard(board);
    }

    // ✅ Delete board
    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable UUID id) {
        boardService.deleteBoard(id);
    }
}
