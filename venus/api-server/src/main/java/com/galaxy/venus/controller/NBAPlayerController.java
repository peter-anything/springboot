package com.galaxy.venus.controller;

import com.galaxy.venus.entity.NBAPlayer;
import com.galaxy.venus.service.NBAPlayerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/nbaplayer")
public class NBAPlayerController {
    @Resource
    private NBAPlayerService nbaPlayerService;

    @RequestMapping("/list")
    public List<NBAPlayer> list() throws CloneNotSupportedException, InterruptedException {
        List<NBAPlayer> retPlayers = new ArrayList<>();
        List<NBAPlayer> players = nbaPlayerService.getAll();
        for (int i = 0; i < 100; i++) {
            for (NBAPlayer player : players) {
                retPlayers.add(player.clone());
            }
        }
        TimeUnit.SECONDS.sleep(10);
        return retPlayers;
    }


}