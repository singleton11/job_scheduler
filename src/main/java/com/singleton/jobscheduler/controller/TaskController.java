package com.singleton.jobscheduler.controller;

import com.singleton.jobscheduler.domain.Task;
import com.singleton.jobscheduler.domain.TaskGuidResponse;
import com.singleton.jobscheduler.domain.TaskResponse;
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
  public ResponseEntity<TaskGuidResponse> createTask() {
    Task task = taskService.createTask();
    taskService.runUpdater(task);
    return ResponseEntity.accepted().body(TaskGuidResponse.builder().guid(task.getGuid()).build());
  }

  @GetMapping("/task/{guid}")
  public ResponseEntity<TaskResponse> getTask(@PathVariable UUID guid) {
    Optional<Task> optionalTask = taskService.getTask(guid);
    if (optionalTask.isPresent()) {
      Task task = optionalTask.get();
      return ResponseEntity.ok().body(TaskResponse.builder()
                                                  .status(task.getStatus())
                                                  .timestamp(task.getTimestamp())
                                                  .build());
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
