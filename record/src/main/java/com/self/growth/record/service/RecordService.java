package com.self.growth.record.service;

import com.self.growth.record.config.MySaTokenContext;
import org.self.growth.model.entity.record.DailyRecordEntity;
import com.self.growth.record.mongo.DailyRecordRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
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

    public List<DailyRecordEntity> upload(final List<DailyRecordEntity> record) {
        final long userId = saTokenContext.loginUserId();
        List<DailyRecordEntity> old = dailyRecordRepository.findAllByUserId(userId);
        List<DailyRecordEntity> merge = new ArrayList<>(record.size() + old.size());
        List<DailyRecordEntity> newRecords = new ArrayList<>(old.size());
        Set<String> repeatFilter = new HashSet<>();

        merge.addAll(record);
        record.forEach(r -> repeatFilter.add(r.getDay()));

        old.forEach(r -> {
            if (repeatFilter.contains(r.getDay())) {
                return;
            }
            repeatFilter.add(r.getDay());
            newRecords.add(r);
            merge.add(r);
        });

        dailyRecordRepository.deleteByUserId(userId);
        merge.forEach(r -> r.setUserId(userId));
        dailyRecordRepository.insert(merge);
        return newRecords;
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
