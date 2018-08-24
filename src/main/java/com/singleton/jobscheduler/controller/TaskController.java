package com.singleton.jobscheduler.controller;

import com.singleton.jobscheduler.domain.Task;
import com.singleton.jobscheduler.service.TaskService;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TaskController {

  private TaskService taskService;

  @PostMapping("task")
  public ResponseEntity<Task> createTask() {
    Task task = taskService.createTask();
    taskService.runUpdater(task);
    return ResponseEntity.accepted().body(task);
  }

  @GetMapping("/task/{guid}")
  public ResponseEntity<Task> getTask(@PathVariable UUID guid) {
    Optional<Task> task = taskService.getTask(guid);
    if (task.isPresent()) {
      return ResponseEntity.ok().body(task.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
