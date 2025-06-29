package com.workhub.demo.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name = "lists")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int position; // To order the lists horizontally (like Trello columns)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    @JsonBackReference
    private Board board;


    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Optional, unless you need tasks returned with list
    private List<Task> tasks = new ArrayList<>();
}
