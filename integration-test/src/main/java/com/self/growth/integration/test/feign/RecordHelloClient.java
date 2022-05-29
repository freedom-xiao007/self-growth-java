package com.self.growth.integration.test.feign;

import org.self.growth.model.entity.DailyRecordEntity;
import org.self.growth.model.vo.ResResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(
        value = "RecordHelloFeign",
        url = "${test.server.record.url}"
)
public interface RecordHelloClient {

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    ResResult<String> hello();

    @RequestMapping(method = RequestMethod.POST, value = "/record/upload")
    ResResult<Void> upload(@RequestBody List<DailyRecordEntity> records);

    @RequestMapping(method = RequestMethod.GET, value = "/record/list")
    ResResult<List<DailyRecordEntity>> list();

    @RequestMapping(method = RequestMethod.POST, value = "/record/deleteAll")
    ResResult<Long> deleteAll();
}
