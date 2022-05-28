package com.self.growth.task.controllers;

import com.self.growth.task.entity.TaskConfig;
import com.self.growth.task.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/update")
    public void update(@RequestBody TaskConfig config) {
        taskService.update(config);
    }

    @GetMapping("/list")
    public List<TaskConfig> list() {
        return taskService.list();
    }
}
