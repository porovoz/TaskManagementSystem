package com.bestapp.taskManagementSystem.controller;

import com.bestapp.taskManagementSystem.dto.task.TaskDTO;
import com.bestapp.taskManagementSystem.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.createTask(taskDTO));
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable(name = "taskId") Integer taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getAllTasks")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getAllAuthUserTasks")
    public ResponseEntity<List<TaskDTO>> getAllAuthenticatedUserTasks() {
        return ResponseEntity.ok(taskService.getAllAuthenticatedUserTasks());
    }

    @PreAuthorize("hasAuthority('USER')")
    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable(name = "taskId") Integer taskId,
                                              @RequestBody TaskDTO taskDTO) {
        TaskDTO updatedTaskDTO = taskService.updateTask(taskId, taskDTO);
        if (updatedTaskDTO != null) {
            return ResponseEntity.ok(updatedTaskDTO);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        //uncompleted
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTaskById(@PathVariable(name = "taskId") Integer taskId) {
        if (taskService.deleteTaskById(taskId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
