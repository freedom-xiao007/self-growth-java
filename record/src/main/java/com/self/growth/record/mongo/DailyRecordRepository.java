package com.self.growth.record.mongo;

import org.self.growth.model.entity.record.DailyRecordEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyRecordRepository extends MongoRepository<DailyRecordEntity, String> {

    List<DailyRecordEntity> findAllByUserId(final long userId);

    void deleteByUserId(final long userId);
}
