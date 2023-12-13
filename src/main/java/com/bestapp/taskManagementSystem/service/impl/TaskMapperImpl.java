package com.bestapp.taskManagementSystem.service.impl;

import com.bestapp.taskManagementSystem.dto.task.TaskDTO;
import com.bestapp.taskManagementSystem.model.TaskEntity;
import com.bestapp.taskManagementSystem.service.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDTO toTaskDTO(TaskEntity taskEntity) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskAuthorId(taskEntity.getTaskAuthor().getId());
        taskDTO.setTaskPerformerId(taskEntity.getTaskPerformer().getId());
        taskDTO.setHeader(taskEntity.getHeader());
        taskDTO.setDescription(taskEntity.getDescription());
//        taskDTO.setTaskId(taskEntity.getId()); // I think there is no need in this.
        taskDTO.setStatus(taskEntity.getStatus());
        taskDTO.setPriority(taskEntity.getPriority());
        return taskDTO;
    }

    @Override
    public TaskEntity toTaskEntity(TaskDTO taskDTO) {
        TaskEntity taskEntity = new TaskEntity();
//        taskEntity.setId(taskDTO.getTaskId()); // I think there is no need in this.
        taskEntity.setHeader(taskDTO.getHeader());
        taskEntity.setDescription(taskDTO.getDescription());
        taskEntity.setStatus(taskDTO.getStatus());
        taskEntity.setPriority(taskEntity.getPriority());
        return taskEntity;
    }

    @Override
    public List<TaskDTO> toTaskDTOList(List<TaskEntity> taskEntityList) {
        List<TaskDTO> taskDTOList = new ArrayList<>();
        for (TaskEntity taskEntity : taskEntityList) {
            TaskDTO taskDTO = toTaskDTO(taskEntity);
            taskDTOList.add(taskDTO);
        }
        return taskDTOList;
    }
}
