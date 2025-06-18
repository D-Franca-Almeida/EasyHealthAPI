package com.fatec.easyhealthapi.dto;

import com.fatec.easyhealthapi.enums.TaskStatus;

public class UpdateTaskStatusDTO {
    private TaskStatus status;

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}