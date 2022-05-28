package com.self.growth.task.service;

import com.self.growth.task.entity.TaskConfig;
import com.self.growth.task.repository.mongo.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record TaskService(TaskRepository taskRepository) {

    public void update(final TaskConfig config) {
        final TaskConfig task = taskRepository.findByGroupAndName(config.getGroup(), config.getName());
        if (task == null) {
            taskRepository.insert(config);
            return;
        }
        taskRepository.delete(task);
        taskRepository.insert(config);
    }

    public List<TaskConfig> list() {
        return taskRepository.findAll();
    }
}
