package com.bestapp.taskManagementSystem.service;

import com.bestapp.taskManagementSystem.dto.task.TaskDTO;
import com.bestapp.taskManagementSystem.model.TaskEntity;

import java.util.List;

public interface TaskMapper {
    TaskDTO toTaskDTO(TaskEntity taskEntity);
    TaskEntity toTaskEntity(TaskDTO taskDTO);
    List<TaskDTO> toTaskDTOList(List<TaskEntity> taskEntityList);
}
