package com.bestapp.taskManagementSystem.service.impl;

import com.bestapp.taskManagementSystem.dto.comment.CommentDTO;
import com.bestapp.taskManagementSystem.exception.notFoundException.CommentNotFoundException;
import com.bestapp.taskManagementSystem.exception.notFoundException.TaskNotFoundException;
import com.bestapp.taskManagementSystem.exception.notFoundException.UserNotFoundException;
import com.bestapp.taskManagementSystem.model.CommentEntity;
import com.bestapp.taskManagementSystem.model.TaskEntity;
import com.bestapp.taskManagementSystem.model.UserEntity;
import com.bestapp.taskManagementSystem.repository.CommentRepository;
import com.bestapp.taskManagementSystem.repository.TaskRepository;
import com.bestapp.taskManagementSystem.repository.UserRepository;
import com.bestapp.taskManagementSystem.service.CommentMapper;
import com.bestapp.taskManagementSystem.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final UserDetails userDetails;
    private final TaskRepository taskRepository;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper, UserRepository userRepository, UserDetails userDetails, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.userRepository = userRepository;
        this.userDetails = userDetails;
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional // This method is not working. UserNotFound exception occurs.
    public CommentDTO addComment(Integer taskId, CommentDTO commentDTO) {
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UserNotFoundException(super.toString()));
        TaskEntity taskEntity = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(super.toString()));
        CommentEntity commentEntity = commentMapper.toCommentEntity(commentDTO);
        commentEntity.setUserEntity(userEntity);
        commentEntity.setTaskEntity(taskEntity);
        return commentMapper.toCommentDTO(commentRepository.save(commentEntity));
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDTO getCommentById(Integer commentId) {
        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(super.toString()));
        return commentMapper.toCommentDTO(commentEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDTO> getCommentsByTaskId(Integer taskId) {
        List<CommentEntity> commentEntityList = commentRepository.findCommentEntitiesByTaskEntity_Id(taskId);
        return commentMapper.toCommentDTOList(commentEntityList);
    }

    @Override
    @Transactional // This method is not working. UserNotFound exception occurs.
    public CommentDTO updateComment(Integer taskId, Integer commentId, CommentDTO commentDTO) {
        String username = userDetails.getUsername();
        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException(super.toString()));
        CommentEntity commentEntity = commentRepository.findByIdAndTaskEntity_Id(commentId, taskId)
                .orElseThrow(() -> new CommentNotFoundException(super.toString()));
        if (commentEntity.getUserEntity().equals(userEntity)) {
            return commentMapper.toCommentDTO(commentRepository.save(commentEntity));
        }
        return null;
    }

    @Override
    @Transactional // This method is not working. UserNotFound exception occurs.
    public boolean deleteComment(Integer taskId, Integer commentId) {
        String username = userDetails.getUsername();
        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException(super.toString()));
        CommentEntity commentEntity = commentRepository.findByIdAndTaskEntity_Id(commentId, taskId)
                .orElseThrow(() -> new CommentNotFoundException(super.toString()));
        if (commentEntity.getUserEntity().equals(userEntity)) {
            commentRepository.delete(commentEntity);
            return true;
        }
        return false;
    }
}
