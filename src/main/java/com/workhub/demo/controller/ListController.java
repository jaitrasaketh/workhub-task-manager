package com.workhub.demo.controller;

import com.workhub.demo.model.ListEntity;
import com.workhub.demo.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/lists")
public class ListController {

    private final ListService listService;

    @Autowired
    public ListController(ListService listService) {
        this.listService = listService;
    }

    // ✅ Create a new list
    @PostMapping
    public ResponseEntity<ListEntity> createList(@RequestBody ListEntity list) {
        ListEntity savedList = listService.saveList(list);
        return ResponseEntity.ok(savedList);
    }

    // ✅ Get a list by ID
    @GetMapping("/{id}")
    public ResponseEntity<ListEntity> getListById(@PathVariable UUID id) {
        Optional<ListEntity> listOpt = listService.getListById(id);
        return listOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Get all lists by board ID
    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<ListEntity>> getListsByBoardId(@PathVariable UUID boardId) {
        List<ListEntity> lists = listService.getListsByBoardId(boardId);
        return ResponseEntity.ok(lists);
    }

    // ✅ Update a list
    @PutMapping("/{id}")
    public ResponseEntity<ListEntity> updateList(@PathVariable UUID id, @RequestBody ListEntity updatedList) {
        Optional<ListEntity> existingOptional = listService.getListById(id);
        if (existingOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ListEntity existing = existingOptional.get();
        existing.setName(updatedList.getName());
        existing.setPosition(updatedList.getPosition());

        // No change to board allowed in update
        ListEntity saved = listService.saveDirect(existing); // new method below

        return ResponseEntity.ok(saved);
    }


    // ✅ Delete a list
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable UUID id) {
        Optional<ListEntity> existingList = listService.getListById(id);
        if (existingList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        listService.deleteList(id);
        return ResponseEntity.noContent().build();
    }
}
