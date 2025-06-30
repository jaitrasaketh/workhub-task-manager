package com.workhub.demo.service;

import com.workhub.demo.client.LLMClient;
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

    private final LLMRecommendationRepository recommendationRepository;
    private final TaskRepository taskRepository;
    private final LLMClient llmClient;

    @Autowired
    public LLMRecommendationService(
            LLMRecommendationRepository recommendationRepository,
            TaskRepository taskRepository,
            LLMClient llmClient
    ) {
        this.recommendationRepository = recommendationRepository;
        this.taskRepository = taskRepository;
        this.llmClient = llmClient;
    }

    // ✅ Manually save a suggestion
    public LLMRecommendation saveRecommendation(UUID taskId, String suggestion) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found: " + taskId));

        LLMRecommendation rec = new LLMRecommendation();
        rec.setTask(task);
        rec.setSuggestion(suggestion);
        rec.setGeneratedAt(Instant.now());

        return recommendationRepository.save(rec);
    }

    // ✅ Fetch all suggestions for a task
    public List<LLMRecommendation> getRecommendationsForTask(UUID taskId) {
        return recommendationRepository.findByTaskId(taskId);
    }

    // ✅ Auto-generate suggestion using LLM and save it
    public LLMRecommendation generateAndSaveSuggestion(UUID taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found: " + taskId));

        String suggestion = llmClient.generateSuggestion(task.getTitle(), task.getDescription());

        LLMRecommendation rec = new LLMRecommendation();
        rec.setTask(task);
        rec.setSuggestion(suggestion);
        rec.setGeneratedAt(Instant.now());

        return recommendationRepository.save(rec);
    }
}
