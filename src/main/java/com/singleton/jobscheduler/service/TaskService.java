package com.singleton.jobscheduler.service;

import com.singleton.jobscheduler.domain.JobStatus;
import com.singleton.jobscheduler.domain.Task;
import com.singleton.jobscheduler.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskService {

  private TaskRepository taskRepository;

  public Task createTask() {
    return taskRepository.save(Task.builder().status(JobStatus.CREATED).build());
  }
}
