package com.bestapp.taskManagementSystem.service;

import com.bestapp.taskManagementSystem.dto.task.TaskDTO;

import java.util.List;

public interface TaskService {
    TaskDTO createTask(TaskDTO taskDTO);
    TaskDTO getTaskById(Integer taskId);
    List<TaskDTO> getAllTasks();
    List<TaskDTO> getAllAuthenticatedUserTasks();
    TaskDTO updateTask(Integer taskId, TaskDTO taskDTO);
    boolean deleteTaskById(Integer taskId);
}
