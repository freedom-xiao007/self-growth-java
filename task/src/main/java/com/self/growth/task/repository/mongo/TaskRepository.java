package com.self.growth.task.repository.mongo;

import com.self.growth.task.entity.TaskConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<TaskConfig, String> {

    TaskConfig findByGroupAndName(final String group, final String name);
}
