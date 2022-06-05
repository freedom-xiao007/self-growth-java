package com.self.growth.task.repository.mongo;

import org.self.growth.model.entity.task.TaskDeleteLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDeleteLogRepository extends MongoRepository<TaskDeleteLog, String> {

    int deleteByUserId(final Long userId);

    List<TaskDeleteLog> findAllByUserId(final Long userId);
}
