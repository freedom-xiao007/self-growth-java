package com.self.growth.task.controllers;

import com.self.growth.task.service.TaskService;
import org.self.growth.model.entity.task.TaskConfig;
import org.self.growth.model.vo.ResResult;
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
    public ResResult<TaskConfig> update(@RequestBody TaskConfig config) {
        return ResResult.success(taskService.update(config));
    }

    @GetMapping("/list")
    public ResResult<List<TaskConfig>> list() {
        return ResResult.success(taskService.list());
    }

    @PostMapping("/deleteAll")
    public ResResult<Integer> deleteAll() {
        return ResResult.success(taskService.deleteAll());
    }

    @PostMapping("/delete")
    public ResResult<Integer> delete(@RequestParam List<String> ids) {
        return ResResult.success(taskService.delete(ids));
    }

    @PostMapping("/sync")
    public ResResult<List<TaskConfig>> sync(@RequestBody List<TaskConfig> configs) {
        return ResResult.success(taskService.sync(configs));
    }
}
