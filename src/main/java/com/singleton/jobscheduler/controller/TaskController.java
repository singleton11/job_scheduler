package com.singleton.jobscheduler.controller;

import com.singleton.jobscheduler.domain.Task;
import com.singleton.jobscheduler.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TaskController {

  private TaskService taskService;

  @PostMapping("task")
  public ResponseEntity<Task> createTask() {
    return ResponseEntity.accepted().body(taskService.createTask());
  }
}
