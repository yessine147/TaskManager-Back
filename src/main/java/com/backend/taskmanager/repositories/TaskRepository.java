package com.backend.taskmanager.repositories;

import com.backend.taskmanager.models.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
