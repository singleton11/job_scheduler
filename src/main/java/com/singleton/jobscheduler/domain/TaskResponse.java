package com.singleton.jobscheduler.domain;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskResponse {

  private Date timestamp;
  private JobStatus status;
}
