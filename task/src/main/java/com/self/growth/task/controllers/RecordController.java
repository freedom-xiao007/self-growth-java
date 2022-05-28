package com.self.growth.task.controllers;

import com.self.growth.task.entity.DailyRecordEntity;
import com.self.growth.task.service.RecordService;
import com.self.growth.task.vo.ResponseVo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/record")
public class RecordController {

    private final RecordService recordService;

    @PostMapping("/upload")
    public ResponseVo<Void> upload(@RequestBody DailyRecordEntity record) {
        record.setUsername("testUser");
        recordService.upload(record);
        return ResponseVo.success();
    }

    @GetMapping("/list")
    public ResponseVo<List<DailyRecordEntity>> list() {
        return ResponseVo.success();
    }
}
