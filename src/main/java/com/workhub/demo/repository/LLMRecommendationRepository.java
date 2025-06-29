package com.workhub.demo.repository;

import com.workhub.demo.model.LLMRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface LLMRecommendationRepository extends JpaRepository<LLMRecommendation, UUID> {
    List<LLMRecommendation> findByTaskId(UUID taskId);
}
