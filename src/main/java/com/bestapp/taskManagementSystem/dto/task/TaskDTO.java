package com.bestapp.taskManagementSystem.dto.task;

import com.bestapp.taskManagementSystem.model.Priority;
import com.bestapp.taskManagementSystem.model.Status;
import lombok.Data;

@Data
public class TaskDTO {
    private Integer taskAuthorId;
    private Integer taskPerformerId;
    private String header;
    private String description;
//    private Integer taskId; // I think there is no need in this.
    private Status status;
    private Priority priority;
}
