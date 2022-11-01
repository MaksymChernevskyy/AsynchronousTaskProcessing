package com.task.asynchronous.service;

import com.task.asynchronous.dto.CreateTaskDto;
import com.task.asynchronous.enums.TaskStatus;
import com.task.asynchronous.model.Task;
import com.task.asynchronous.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class TaskService {
    private ConversionService conversionService;
    private TaskRepository repository;

    public Task createTask(CreateTaskDto taskDto) {
        Task task = repository.save(conversionService.convert(taskDto, Task.class));
        processTask(task);
        char[] inputChars = task.getInput().toCharArray();
        char[] patternChars = task.getPattern().toCharArray();
        task.getResult().setPosition(setPosition(inputChars, patternChars));
        task.getResult().setTypos(findDifferencesCount(inputChars, patternChars));
        saveTask(task);
        return task;
    }

    private int setPosition(final char[] inputChars, final char[] patternChars) {
        int position = 0;
        boolean outerBreak = false;
        for (char inputChar : inputChars) {
            for (char patternChar : patternChars) {
                position += 1;
                if (inputChar != patternChar) {
                    outerBreak = true;
                    break;
                }
            }
            if (outerBreak) {
                break;
            }
        }
        return position;
    }

    private int findDifferencesCount(final char[] inputChars, final char[] parentChars) {
        int differencesCount = 0;
        for (int i = 0, j = 0; i < inputChars.length && j < parentChars.length; ) {
            boolean isEquals = inputChars[i] != (parentChars[j]);
            if (isEquals) {
                differencesCount++;
                i++;
                j++;
            } else if (!isEquals) {
                i++;
            } else {
                j++;
            }
        }
        return differencesCount;
    }

    @Async
    public void processTask(Task task) {
        for (int i = 0; i <= 100; i++) {
            task.setTaskStatus(TaskStatus.RUNNING);
            task.setProgress(i + "%");
            saveTask(task);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        task.setTaskStatus(TaskStatus.FINISHED);

        saveTask(task);
    }

    private void saveTask(Task task) {
        repository.save(task);
    }

    public Optional<Task> getTaskById(Long id) {
        return repository.findById(id);
    }

    public List<Task> getAll() {
        return repository.findAll();
    }


}
