package com.singleton.jobscheduler.service;

import com.singleton.jobscheduler.domain.JobStatus;
import com.singleton.jobscheduler.domain.Task;
import com.singleton.jobscheduler.repository.TaskRepository;
import java.time.OffsetDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TaskService {

  private TaskRepository taskRepository;
  private TaskScheduler taskScheduler;

  public Task createTask() {
    return taskRepository.save(Task.builder().status(JobStatus.CREATED).build());
  }

  @Async
  public void runUpdater(Task task) {
    task.setStatus(JobStatus.RUNNING);
    taskRepository.save(task);
    log.info("Task {} run", task.getGuid());

    taskScheduler.schedule(() -> {
      task.setStatus(JobStatus.FINISHED);
      taskRepository.save(task);
      log.info("Task {} finished", task.getGuid());
    }, new Date(OffsetDateTime.now().plusMinutes(2).toInstant().toEpochMilli()));
  }
}
