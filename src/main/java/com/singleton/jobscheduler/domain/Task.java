package com.singleton.jobscheduler.domain;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Data
@Entity
@TypeDef(name = "job_status", typeClass = PostgreSQLEnumType.class)
public class Task {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID guid;

  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  private Date timestamp;

  @Enumerated(EnumType.STRING)
  @Type(type = "job_status")
  private JobStatus status;
}
