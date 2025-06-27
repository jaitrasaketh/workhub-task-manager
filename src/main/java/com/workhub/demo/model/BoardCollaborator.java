package com.workhub.demo.model;

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
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // You can also add roles (e.g., viewer/editor) if needed later
    // @Enumerated(EnumType.STRING)
    // private CollaboratorRole role;
}
