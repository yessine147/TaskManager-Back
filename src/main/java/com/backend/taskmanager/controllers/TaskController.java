package com.backend.taskmanager.controllers;

import com.backend.taskmanager.models.entities.Task;
import com.backend.taskmanager.services.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Creates a new task.
     *
     * @param task The task to be created.
     * @return ResponseEntity with the created task and HTTP status CREATED (201).
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    /**
     * Retrieves a paginated list of tasks optionally filtered by a query string.
     *
     * @param page  The page number (default: 0).
     * @param size  The page size (default: 2).
     * @param query The query string for filtering tasks (default: "").
     * @return ResponseEntity with the paginated list of tasks and HTTP status OK (200).
     */
    @GetMapping
    public ResponseEntity<Page<Task>> getAllTasks(
            @RequestParam(name = "page",defaultValue = "0") Integer page,
            @RequestParam(name = "size",defaultValue = "2") Integer size,
            @RequestParam(name = "query", defaultValue = "") String query )
    {
        Pageable pageable = PageRequest.of(page, size);
        Page<Task> pageUser = taskService.searchTasksByQuery(query ,pageable);
        return new ResponseEntity<>(pageUser, HttpStatus.OK);
    }

    /**
     * Retrieves a task by its ID.
     *
     * @param id The ID of the task to retrieve.
     * @return ResponseEntity with the retrieved task and HTTP status OK (200) if found,
     * or HTTP status NOT_FOUND (404) if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable Long id) {
        try {
            Task task = taskService.getTaskById(id);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Updates a task with the specified ID.
     *
     * @param id   The ID of the task to update.
     * @param task The updated task details.
     * @return ResponseEntity with the updated task and HTTP status OK (200) if updated successfully,
     * or HTTP status NOT_FOUND (404) if task not found,
     * or HTTP status BAD_REQUEST (400) if the request is invalid,
     * or HTTP status INTERNAL_SERVER_ERROR (500) for other errors.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable Long id, @RequestBody Task task) {
        try {
            Task updatedTask = taskService.updateTask(id, task);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a task by its ID.
     *
     * @param id The ID of the task to delete.
     * @return ResponseEntity with a success message and HTTP status OK (200) if deleted successfully,
     * or HTTP status NOT_FOUND (404) if task not found,
     * or HTTP status INTERNAL_SERVER_ERROR (500) for other errors.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
