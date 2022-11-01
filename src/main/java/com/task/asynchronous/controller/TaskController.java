package com.task.asynchronous.controller;

import com.task.asynchronous.dto.CreateTaskDto;
import com.task.asynchronous.model.Task;
import com.task.asynchronous.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private TaskService taskService;

    @PostMapping("/")
    public ResponseEntity<Task> responseEntity(@Valid CreateTaskDto taskCreateDto){
        return ResponseEntity.ok(taskService.createTask(taskCreateDto));
    }

    @GetMapping("{/id}")
    public ResponseEntity<Optional<Task>> getTask(@RequestParam Long id){
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAll());
    }
}
