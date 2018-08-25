package com.singleton.jobscheduler.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.singleton.jobscheduler.domain.JobStatus;
import com.singleton.jobscheduler.domain.Task;
import com.singleton.jobscheduler.service.TaskService;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {

  private MockMvc mockMvc;

  @Mock
  private TaskService taskService;

  @InjectMocks
  private TaskController taskController;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
  }

  @Test
  public void createTask() throws Exception {
    UUID guid = UUID.randomUUID();
    when(taskService.createTask()).thenReturn(Task.builder()
                                                  .guid(guid)
                                                  .status(JobStatus.CREATED)
                                                  .timestamp(new Date())
                                                  .build());

    mockMvc.perform(post("/task"))
           .andExpect(status().isAccepted())
           .andExpect(jsonPath("$.guid").value(guid.toString()));
  }

  @Test
  public void getTaskPositive() throws Exception {
    UUID guid = UUID.randomUUID();
    Date timestamp = new Date();
    when(taskService.getTask(guid)).thenReturn(Optional.of(Task.builder()
                                                               .guid(guid)
                                                               .status(JobStatus.RUNNING)
                                                               .timestamp(timestamp)
                                                               .build()));

    mockMvc.perform(get("/task/{guid}", guid))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.timestamp").value(timestamp))
           .andExpect(jsonPath("$.status").value("running"));
  }

  @Test
  public void getTaskNotFound() throws Exception {
    UUID guid = UUID.randomUUID();
    when(taskService.getTask(guid)).thenReturn(Optional.empty());

    mockMvc.perform(get("/task/{guid}", guid)).andExpect(status().isNotFound());
  }

  @Test
  public void getTaskBadRequest() throws Exception {
    mockMvc.perform(get("/task/{guid}", "test"))
           .andExpect(status().isBadRequest());
  }

}
