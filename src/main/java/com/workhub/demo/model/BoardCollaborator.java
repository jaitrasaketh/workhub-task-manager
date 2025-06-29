package com.workhub.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "board_collaborators")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardCollaborator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    @JsonIgnoreProperties({"collaborators", "lists", "owner"})
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"boardsOwned", "assignments", "collaborations", "password"})
    private User user;

    // You can also add roles (e.g., viewer/editor) if needed later
    // @Enumerated(EnumType.STRING)
    // private CollaboratorRole role;
}
