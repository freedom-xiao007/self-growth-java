package com.self.growth.record.controller;

import org.self.growth.model.entity.DailyRecordEntity;
import com.self.growth.record.service.RecordService;
import lombok.AllArgsConstructor;
import org.self.growth.model.vo.ResResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/record")
public class RecordController {

    private final RecordService recordService;

    @PostMapping("/upload")
    public ResResult<List<DailyRecordEntity>> upload(@RequestBody List<DailyRecordEntity> records) {
        return ResResult.success(recordService.upload(records));
    }

    @GetMapping("/list")
    public ResResult<List<DailyRecordEntity>> list() {
        return ResResult.success(recordService.list());
    }

    @PostMapping("/deleteAll")
    public ResResult<Long> deleteAll() {
        return ResResult.success(recordService.deleteAll());
    }
}
