package com.workhub.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "llm_recommendations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LLMRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    @JsonBackReference
    private Task task;


    @Column(columnDefinition = "TEXT", nullable = false)
    private String suggestion;

    @Column(name = "generated_at", nullable = false, updatable = false)
    private Instant generatedAt;
}
