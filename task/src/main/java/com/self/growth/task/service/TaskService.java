package com.self.growth.task.service;

import com.self.growth.task.config.MySaTokenContext;
import com.self.growth.task.repository.mongo.TaskRepository;
import org.self.growth.model.entity.task.TaskConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    /**
     * 合并本地和数据的任务数据
     * 返回相对于本地新增定时任务数据
     * @param configs 本地任务数据
     * @return 相对于本地新增定时任务数据
     */
    public List<TaskConfig> sync(List<TaskConfig> configs) {
        final long userId = saTokenContext.loginUserId();

        List<TaskConfig> tasksOfDb = taskRepository.findAllByUserId(userId);
        List<TaskConfig> newTasks = new ArrayList<>(tasksOfDb.size());
        final List<TaskConfig> mergeTasks = new ArrayList<>(tasksOfDb.size() + configs.size());

        final Set<String> repeatFilter = new HashSet<>();
        mergeTasks.addAll(configs);
        configs.forEach(task -> {
            final String flag = String.format("%s:%s", task.getGroup(), task.getName());
            repeatFilter.add(flag);
        });

        tasksOfDb.forEach(task -> {
            final String flag = String.format("%s:%s", task.getGroup(), task.getName());
            if (repeatFilter.contains(flag)) {
                return;
            }
            repeatFilter.add(flag);
            mergeTasks.add(task);
            newTasks.add(task);
        });

        taskRepository.deleteByUserId(userId);
        mergeTasks.forEach(i -> i.setUserId(userId));
        taskRepository.insert(mergeTasks);
        return newTasks;
    }
}
