package com.self.growth.integration.test.record;

import com.self.growth.integration.test.BaseServerTest;
import com.self.growth.integration.test.feign.RecordHelloClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.self.growth.model.entity.DailyRecordEntity;
import org.self.growth.model.vo.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
public class RecordServerTest extends BaseServerTest {

    @Autowired
    private RecordHelloClient recordHelloClient;

    @Test
    public void helloTest() {
        ResResult<String> res = recordHelloClient.hello();
        log.info(res.toString());
        Assertions.assertEquals(200, res.getCode());
    }

    @Test
    public void uploadAndClear() {
        final List<DailyRecordEntity> records = Arrays.asList(
                DailyRecordEntity.builder()
                        .books(1)
                        .build(),
                DailyRecordEntity.builder()
                        .books(2)
                        .build()
        );
        ResResult<Void> uploadRes = recordHelloClient.upload(records);
        log.info(uploadRes.toString());
        Assertions.assertEquals(200, uploadRes.getCode());

        ResResult<List<DailyRecordEntity>> listRes = recordHelloClient.list();
        log.info(listRes.toString());
        Assertions.assertEquals(2, listRes.getData().size());

        ResResult<Long> deleteRes = recordHelloClient.deleteAll();
        log.info(deleteRes.toString());
        Assertions.assertEquals(2L, deleteRes.getData());

        listRes = recordHelloClient.list();
        log.info(listRes.toString());
        Assertions.assertEquals(0, listRes.getData().size());
    }
}
