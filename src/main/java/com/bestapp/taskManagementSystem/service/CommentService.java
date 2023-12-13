package com.bestapp.taskManagementSystem.service;

import com.bestapp.taskManagementSystem.dto.comment.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO addComment(Integer taskId, CommentDTO commentDTO);
    CommentDTO getCommentById(Integer commentId);
    List<CommentDTO> getCommentsByTaskId(Integer taskId);
    CommentDTO updateComment(Integer taskId, Integer commentId, CommentDTO commentDTO);
    boolean deleteComment(Integer taskId, Integer commentId);
}
