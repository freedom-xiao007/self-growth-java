package com.self.growth.integration.test.feign;

import org.self.growth.model.entity.task.TaskConfig;
import org.self.growth.model.vo.ResResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        value = "TaskClient",
        url = "${test.server.task.url}"
)
public interface TaskClient {

    @RequestMapping(method = RequestMethod.POST, value = "/task/update")
    ResResult<TaskConfig> update(@RequestBody TaskConfig config);

    @RequestMapping(method = RequestMethod.GET, value = "/task/list")
    ResResult<List<TaskConfig>> list();

    @RequestMapping(method = RequestMethod.POST, value = "/task/delete")
    ResResult<Integer> delete(@RequestParam String ids);

    @RequestMapping(method = RequestMethod.POST, value = "/task/deleteAll")
    ResResult<Integer> deleteAll();
}
