package com.workhub.demo.controller;

import com.workhub.demo.dto.LLMRequest;
import com.workhub.demo.model.LLMRecommendation;
import com.workhub.demo.service.LLMRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/llm-recommendations")
public class LLMRecommendationController {

    private final LLMRecommendationService llmService;

    @Autowired
    public LLMRecommendationController(LLMRecommendationService llmService) {
        this.llmService = llmService;
    }

    // 🔹 Save LLM suggestion
    @PostMapping
    public LLMRecommendation addRecommendation(@RequestBody LLMRequest request) {
        return llmService.saveRecommendation(request.getTaskId(), request.getSuggestion());
    }

    // 🔹 Get suggestions for a task
    @GetMapping("/task/{taskId}")
    public List<LLMRecommendation> getRecommendations(@PathVariable UUID taskId) {
        return llmService.getRecommendationsForTask(taskId);
    }
}
