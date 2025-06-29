package com.workhub.demo.service;

import com.workhub.demo.model.ListEntity;
import com.workhub.demo.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ListService {

    private final ListRepository listRepository;

    @Autowired
    public ListService(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    // Get a specific list
    public Optional<ListEntity> getListById(UUID id) {
        return listRepository.findById(id);
    }

    // Get all lists in a board
    public List<ListEntity> getListsByBoardId(UUID boardId) {
        return listRepository.findByBoardId(boardId);
    }

    // Save or update a list
    public ListEntity saveList(ListEntity list) {
        return listRepository.save(list);
    }

    // Delete a list
    public void deleteList(UUID id) {
        listRepository.deleteById(id);
    }
}
