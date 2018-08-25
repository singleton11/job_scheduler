package com.singleton.jobscheduler.domain;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskGuidResponse {

  private UUID guid;
}
