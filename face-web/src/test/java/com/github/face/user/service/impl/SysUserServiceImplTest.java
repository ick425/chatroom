package com.github.face.user.service.impl;

import com.github.face.client.ESClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //加载websocket环境
@Slf4j
class SysUserServiceImplTest {

    @Autowired
    ESClient esClient;

    @Test
    public void create() throws IOException {
        esClient.addIndex("idx-0001索引");
        log.info("创建索引");
    }

    @Test
    public void delIndex() throws IOException {
        esClient.delIndex("idx-0001索引");
        log.info("删除索引");
    }
}