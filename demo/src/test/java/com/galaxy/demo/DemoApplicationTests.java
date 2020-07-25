package com.galaxy.demo;

import com.galaxy.demo.dao.NBAPlayerDao;
import com.galaxy.demo.service.NBAPlayerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {
    @Resource
    private NBAPlayerService nbaPlayerService;

    @Test
    void testNBAPlayerList() {
        nbaPlayerService.getALL();
    }

}
