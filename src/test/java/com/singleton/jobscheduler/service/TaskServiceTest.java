package com.singleton.jobscheduler.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.singleton.jobscheduler.domain.JobStatus;
import com.singleton.jobscheduler.domain.Task;
import com.singleton.jobscheduler.repository.TaskRepository;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.scheduling.TaskScheduler;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

  @Mock
  private TaskRepository taskRepository;

  @Mock
  private TaskScheduler taskScheduler;

  @InjectMocks
  private TaskService taskService;

  @Captor
  private ArgumentCaptor<Task> taskArgumentCaptor;

  @Captor
  private ArgumentCaptor<Date> dateArgumentCaptor;

  @Test
  public void createTaskSavesTaskWithCreatedStatus() {
    taskService.createTask();
    verify(taskRepository, times(1)).save(taskArgumentCaptor.capture());

    assertEquals(JobStatus.CREATED, taskArgumentCaptor.getValue().getStatus());
  }

  @Test
  public void taskSchedulerAboutTwoMinutesAfterRunUpdaterCalled() {
    Task task = Task.builder().status(JobStatus.CREATED).timestamp(new Date()).build();
    taskService.runUpdater(task);

    verify(taskScheduler, times(1)).schedule(any(),
                                                                    dateArgumentCaptor.capture());
    assertTrue(LongStream.range(119000, 120000)
                         .boxed()
                         .collect(Collectors.toSet())
                         .contains(dateArgumentCaptor.getValue().getTime() - new Date().getTime()));
  }

  @Test
  public void getTaskShouldCallFindByIdWithConcreteUUID() {
    UUID uuid = UUID.randomUUID();
    taskService.getTask(uuid);

    verify(taskRepository, times(1)).findById(eq(uuid));
  }
}
