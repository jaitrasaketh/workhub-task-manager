package com.workhub.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "users") // optional if table is named differently
@Data                   // Generates getters/setters/toString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // UUID auto-gen (Hibernate handles this)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    private String name;

    @Column(name = "profile_pic_url")
    private String profilePicUrl;

    // A user can own multiple boards
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonIgnore // Prevent infinite recursion when serializing Board → User → Boards...
    private List<Board> boards = new ArrayList<>();

    // A user can collaborate on many boards
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BoardCollaborator> collaborations = new ArrayList<>();

    // A user can be assigned many tasks
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Assignment> assignments = new ArrayList<>();
}
