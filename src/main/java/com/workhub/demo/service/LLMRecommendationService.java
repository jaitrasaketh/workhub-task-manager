package com.workhub.demo.service;

import com.workhub.demo.model.LLMRecommendation;
import com.workhub.demo.model.Task;
import com.workhub.demo.repository.LLMRecommendationRepository;
import com.workhub.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class LLMRecommendationService {

    @Autowired
    private LLMRecommendationRepository llmRecommendationRepository;

    @Autowired
    private TaskRepository taskRepository;

    // 🔹 Use Case 1: Save a new recommendation for a task
    public LLMRecommendation saveRecommendation(UUID taskId, String suggestion) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        LLMRecommendation recommendation = new LLMRecommendation();
        recommendation.setTask(task);
        recommendation.setSuggestion(suggestion);
        recommendation.setGeneratedAt(Instant.now());

        return llmRecommendationRepository.save(recommendation);
    }

    // 🔹 Use Case 2: Get all LLM suggestions for a task
    public List<LLMRecommendation> getRecommendationsForTask(UUID taskId) {
        return llmRecommendationRepository.findByTaskId(taskId);
    }
}
