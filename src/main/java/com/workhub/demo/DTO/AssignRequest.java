package com.workhub.demo.DTO;

import java.util.UUID;

public class AssignRequest {

    private UUID taskId;
    private UUID userId;

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
