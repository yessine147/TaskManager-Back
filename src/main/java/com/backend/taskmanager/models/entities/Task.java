package com.backend.taskmanager.models.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Table(name = "TASKS_LIST")
@Entity
@Data
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Boolean completed;

}
