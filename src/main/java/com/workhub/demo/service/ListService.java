package com.workhub.demo.service;

import com.workhub.demo.model.Board;
import com.workhub.demo.model.ListEntity;
import com.workhub.demo.repository.BoardRepository;
import com.workhub.demo.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ListService {

    private final ListRepository listRepository;
    private final BoardRepository boardRepository;

    @Autowired
    public ListService(ListRepository listRepository, BoardRepository boardRepository) {
        this.listRepository = listRepository;
        this.boardRepository = boardRepository;
    }

    public Optional<ListEntity> getListById(UUID id) {
        return listRepository.findById(id);
    }

    public List<ListEntity> getListsByBoardId(UUID boardId) {
        return listRepository.findByBoardId(boardId);
    }

    public ListEntity saveList(ListEntity list) {
        if (list.getBoard() == null || list.getBoard().getId() == null) {
            throw new IllegalArgumentException("Board ID must not be null when creating a list.");
        }

        UUID boardId = list.getBoard().getId();
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found: " + boardId));

        list.setBoard(board); // make sure it's a managed entity
        return listRepository.save(list);
    }
    public ListEntity saveDirect(ListEntity list) {
        return listRepository.save(list); // skips board null check
    }


    public void deleteList(UUID id) {
        listRepository.deleteById(id);
    }
}
