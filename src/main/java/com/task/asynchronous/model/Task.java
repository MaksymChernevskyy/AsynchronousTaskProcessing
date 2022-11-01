package com.task.asynchronous.model;

import com.task.asynchronous.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String input;
    private String pattern;
    private TaskStatus taskStatus;
    private String progress;
    @Embedded
    private Result result = new Result();
}
