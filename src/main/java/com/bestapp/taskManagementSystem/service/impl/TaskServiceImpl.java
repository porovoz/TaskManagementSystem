package com.bestapp.taskManagementSystem.service.impl;

import com.bestapp.taskManagementSystem.dto.task.TaskDTO;
import com.bestapp.taskManagementSystem.exception.notFoundException.TaskNotFoundException;
import com.bestapp.taskManagementSystem.exception.notFoundException.UserNotFoundException;
import com.bestapp.taskManagementSystem.model.TaskEntity;
import com.bestapp.taskManagementSystem.model.UserEntity;
import com.bestapp.taskManagementSystem.repository.CommentRepository;
import com.bestapp.taskManagementSystem.repository.TaskRepository;
import com.bestapp.taskManagementSystem.repository.UserRepository;
import com.bestapp.taskManagementSystem.service.TaskMapper;
import com.bestapp.taskManagementSystem.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserDetails userDetails;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper,
                           UserDetails userDetails, UserRepository userRepository,
                           CommentRepository commentRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.userDetails = userDetails;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional // This method is not working. UserNotFound exception occurs.
    public TaskDTO createTask(TaskDTO taskDTO) {
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UserNotFoundException(super.toString()));
        TaskEntity taskEntity = taskRepository.save(taskMapper.toTaskEntity(taskDTO));
        taskEntity.setTaskAuthor(userEntity);
//        taskEntity.setTaskPerformer(userEntity); // that's the question what user to set here...
        TaskDTO savedTaskDTO = taskMapper.toTaskDTO(taskRepository.save(taskEntity));
        log.info("Task was created successfully.");
        return savedTaskDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public TaskDTO getTaskById(Integer taskId) {
        TaskEntity taskEntity = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(super.toString()));
        return taskMapper.toTaskDTO(taskEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> getAllTasks() {
        List<TaskEntity> taskEntityList = taskRepository.findAll();
        return taskMapper.toTaskDTOList(taskEntityList);
    }

    @Override
    @Transactional(readOnly = true) // not working, maybe something wrong
    public List<TaskDTO> getAllAuthenticatedUserTasks() {
//        List<TaskEntity> taskEntityList = taskRepository.findAllByUserEntityEmail(userDetails.getUsername());
//        return taskMapper.toTaskDTOList(taskEntityList); // this method doesn't work
        return new ArrayList<>(); // This is temporarily code string
    }

    @Override
    @Transactional // This method is not working. UserNotFound exception occurs.
    public TaskDTO updateTask(Integer taskId, TaskDTO taskDTO) {
        String userName = userDetails.getUsername();
        UserEntity userEntity = userRepository.findByEmail(userName)
                        .orElseThrow(() -> new UserNotFoundException(super.toString()));
        TaskEntity taskEntity = taskRepository.findById(taskId) // not sure with taskId  taskDTO.getTaskId()
                        .orElseThrow(() -> new TaskNotFoundException(super.toString()));
        if (taskEntity.getTaskAuthor().equals(userEntity)) {
            TaskDTO updatedTaskDTO = taskMapper.toTaskDTO(taskRepository.save(taskEntity));
            log.info("Task with id: {} was updated successfully.", taskId); // not sure with taskId taskDTO.getTaskId()
            return updatedTaskDTO;
        }
        return null;
    }

    @Override
    @Transactional // This method is not working. UserNotFound exception occurs.
    public boolean deleteTaskById(Integer taskId) {
        String userName = userDetails.getUsername();
        UserEntity userEntity = userRepository.findByEmail(userName)
                .orElseThrow(() -> new UserNotFoundException(super.toString()));
        TaskEntity taskEntity = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(super.toString()));
        if (taskEntity.getTaskAuthor().equals(userEntity)) {
            commentRepository.deleteAllByTaskEntity_Id(taskId);
            taskRepository.deleteById(taskId);
            log.info("Task with id: {} was deleted successfully.", taskId);
            return true;
        }
        return false;
    }
}
