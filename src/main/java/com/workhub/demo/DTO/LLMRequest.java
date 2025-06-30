package com.workhub.demo.dto;

import java.util.UUID;

public class LLMRequest {

    private UUID taskId;
    private String suggestion;

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}
