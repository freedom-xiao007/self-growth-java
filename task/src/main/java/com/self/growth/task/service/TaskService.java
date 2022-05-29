package com.self.growth.task.service;

import com.self.growth.task.config.MySaTokenContext;
import com.self.growth.task.repository.mongo.TaskRepository;
import org.self.growth.model.entity.task.TaskConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record TaskService(MySaTokenContext saTokenContext, TaskRepository taskRepository) {

    public TaskConfig update(TaskConfig config) {
        final long userId = saTokenContext.loginUserId();
        config.setUserId(userId);

        final TaskConfig task = taskRepository.findByGroupAndNameAndUserId(config.getGroup(), config.getName(), userId);
        if (task == null) {
            taskRepository.insert(config);
            return config;
        }

        taskRepository.delete(task);
        taskRepository.insert(config);
        return config;
    }

    public List<TaskConfig> list() {
        final long userId = saTokenContext.loginUserId();
        return taskRepository.findAllByUserId(userId);
    }

    public Integer deleteAll() {
        final long userId = saTokenContext.loginUserId();
        return taskRepository.deleteByUserId(userId);
    }

    public Integer delete(List<String> ids) {
        final long userId = saTokenContext.loginUserId();
        return taskRepository.deleteAllByIdAndUserId(ids, userId);
    }
}
