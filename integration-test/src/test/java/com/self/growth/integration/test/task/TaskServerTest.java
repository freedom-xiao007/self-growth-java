package com.self.growth.integration.test.task;

import com.self.growth.integration.test.BaseServerTest;
import com.self.growth.integration.test.feign.TaskClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.self.growth.model.entity.task.TaskConfig;
import org.self.growth.model.vo.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class TaskServerTest extends BaseServerTest {

    @Autowired
    private TaskClient taskClient;

    @Test
    public void taskAddAndUpdateAndDelete() {
        log.info(taskClient.deleteAll().toString());

        TaskConfig taskConfig = TaskConfig.builder()
                .group("group")
                .name("name")
                .description("1")
                .build();
        ResResult<TaskConfig> updateRes = taskClient.update(taskConfig);
        log.info(updateRes.toString());
        Assertions.assertEquals(200, updateRes.getCode());

        ResResult<List<TaskConfig>> listRes = taskClient.list();
        log.info(listRes.toString());
        Assertions.assertEquals(200, listRes.getCode());
        Assertions.assertEquals("group", listRes.getData().get(0).getGroup());
        Assertions.assertEquals("name", listRes.getData().get(0).getName());
        Assertions.assertEquals("1", listRes.getData().get(0).getDescription());

        taskConfig.setDescription("2");
        updateRes = taskClient.update(taskConfig);
        log.info(updateRes.toString());
        Assertions.assertEquals(200, updateRes.getCode());

        listRes = taskClient.list();
        log.info(listRes.toString());
        Assertions.assertEquals(200, listRes.getCode());
        Assertions.assertEquals("group", listRes.getData().get(0).getGroup());
        Assertions.assertEquals("name", listRes.getData().get(0).getName());
        Assertions.assertEquals("2", listRes.getData().get(0).getDescription());

        ResResult<Integer> deleteRes = taskClient.delete(listRes.getData().get(0).getId());
        Assertions.assertEquals(200, deleteRes.getCode());
        Assertions.assertEquals(1, deleteRes.getData());

        listRes = taskClient.list();
        log.info(listRes.toString());
        Assertions.assertEquals(200, listRes.getCode());
        Assertions.assertEquals(0, listRes.getData().size());
    }
}
