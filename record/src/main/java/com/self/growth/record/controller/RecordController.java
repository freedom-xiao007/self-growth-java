package com.self.growth.record.controller;

import com.self.growth.record.entity.DailyRecordEntity;
import com.self.growth.record.service.RecordService;
import com.self.growth.record.vo.ResResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/record")
public class RecordController {

    private final RecordService recordService;

    @PostMapping("/upload")
    public ResResult<?> upload(@RequestBody DailyRecordEntity record) {
        record.setUsername("testUser");
        recordService.upload(record);
        return ResResult.success("上传成功");
    }

    @GetMapping("/list")
    public ResResult<List<DailyRecordEntity>> list() {
        return ResResult.success(recordService.list());
    }
}
