package com.singleton.jobscheduler.repository;

import com.singleton.jobscheduler.domain.Task;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, UUID> {

}
