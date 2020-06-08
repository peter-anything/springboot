package com.galaxy.api.nba;

/**
 * @Auther: peter
 * @Date: 2020/6/9 01:29
 * @Description:
 */

import com.galaxy.mecury.entity.NBAPlayer;
import com.galaxy.mecury.service.nba.NBAPlayerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/nbaplayer")
public class NBAApi {
    @Resource
    private NBAPlayerService nbaPlayerService;

    @RequestMapping("/list")
    public List<NBAPlayer> list() {
        return null;
    }
}