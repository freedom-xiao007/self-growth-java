package com.self.growth.task.repository.mongo;

import org.self.growth.model.entity.task.TaskConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<TaskConfig, String> {

    TaskConfig findByGroupAndNameAndUserId(final String group, final String name, final Long userId);

    List<TaskConfig> findAllByUserId(final Long userId);

    int deleteByUserId(final Long userId);

    int deleteAllByIdAndUserId(Iterable<? extends String> ids, final Long userId);
}
