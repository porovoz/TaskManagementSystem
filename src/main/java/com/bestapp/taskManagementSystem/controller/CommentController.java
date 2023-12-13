package com.bestapp.taskManagementSystem.controller;

import com.bestapp.taskManagementSystem.dto.comment.CommentDTO;
import com.bestapp.taskManagementSystem.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@Tag(name = "Comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/{taskId}")
    public ResponseEntity<CommentDTO> addComment(@PathVariable(name = "taskId") Integer taskId,
                                                 @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.addComment(taskId, commentDTO));
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable(name = "commentId") Integer commentId) {
        return ResponseEntity.ok(commentService.getCommentById(commentId));
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/{taskId}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByTaskId(@PathVariable(name = "taskId") Integer taskId) {
        return ResponseEntity.ok(commentService.getCommentsByTaskId(taskId));
    }

    @PreAuthorize("hasAuthority('USER')")
    @PatchMapping("/{taskId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable(name = "taskId") Integer taskId,
                                                    @PathVariable(name = "commentId") Integer commentId,
                                                    @RequestBody CommentDTO commentDTO) {
        CommentDTO updatedComment = commentService.updateComment(taskId, commentId, commentDTO);
        if (updatedComment != null) {
            return ResponseEntity.ok(updatedComment);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/{taskId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "taskId") Integer taskId,
                                           @PathVariable(name = "commentId") Integer commentId) {
        if (commentService.deleteComment(taskId, commentId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
