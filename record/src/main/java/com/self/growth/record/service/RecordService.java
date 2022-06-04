package com.self.growth.record.service;

import com.self.growth.record.config.MySaTokenContext;
import org.self.growth.model.entity.DailyRecordEntity;
import com.self.growth.record.mongo.DailyRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecordService {

    private final MySaTokenContext saTokenContext;
    private final DailyRecordRepository dailyRecordRepository;

    public RecordService(MySaTokenContext saTokenContext, DailyRecordRepository dailyRecordRepository) {
        this.saTokenContext = saTokenContext;
        this.dailyRecordRepository = dailyRecordRepository;
    }

    public void upload(final List<DailyRecordEntity> record) {
        final long userId = saTokenContext.loginUserId();
        dailyRecordRepository.deleteByUserId(userId);

        record.forEach(r -> r.setUserId(userId));
        dailyRecordRepository.insert(record);
    }

    public List<DailyRecordEntity> list() {
        return dailyRecordRepository.findAllByUserId(saTokenContext.loginUserId());
    }

    public Long deleteAll() {
        final long userId = saTokenContext.loginUserId();
        Set<String> ids = dailyRecordRepository.findAllByUserId(userId)
                .stream()
                .map(DailyRecordEntity::getId)
                .collect(Collectors.toSet());
        if (ids.isEmpty()) {
            return 0L;
        }
        dailyRecordRepository.deleteAllById(ids);
        return (long) ids.size();
    }
}
