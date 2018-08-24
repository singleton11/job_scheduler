package com.singleton.jobscheduler.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum JobStatus {
  @JsonProperty("created")
  CREATED,
  @JsonProperty("running")
  RUNNING,
  @JsonProperty("finished")
  FINISHED
}
