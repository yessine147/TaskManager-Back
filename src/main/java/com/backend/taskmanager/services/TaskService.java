package com.backend.taskmanager.services;

import com.backend.taskmanager.models.entities.Task;
import com.backend.taskmanager.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Page<Task> searchTasksByQuery(String query, Pageable pageable) {
        return taskRepository.findByTitleContainingIgnoreCase(query, pageable);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task with id " + id + " not found"));
    }

    public Task updateTask(Long id, Task updatedTask) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task with id " + id + " not found");
        }
        Task existingTask = getTaskById(id);

        return taskRepository.save(Task.builder()
                .id(existingTask.getId())
                .title(updatedTask.getTitle() != null ? updatedTask.getTitle() : existingTask.getTitle())
                .description(updatedTask.getDescription() != null ? updatedTask.getDescription() : existingTask.getDescription())
                .completed(updatedTask.getCompleted() != null ? updatedTask.getCompleted() : existingTask.getCompleted())
                .build());
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task with id " + id + " not found");
        }
        taskRepository.deleteById(id);
    }
}
