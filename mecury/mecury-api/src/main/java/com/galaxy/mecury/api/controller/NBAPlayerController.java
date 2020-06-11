package com.galaxy.mecury.api.controller;

import com.galaxy.mecury.entity.NBAPlayer;
import com.galaxy.mecury.service.NBAPlayerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: peter
 * @Date: 2020/6/11 00:37
 * @Description:
 */
@RestController
@RequestMapping("/nbaplayer")
public class NBAPlayerController {
    @Resource
    private NBAPlayerService nbaPlayerService;

    @RequestMapping("/list")
    public List<NBAPlayer> list() {
        return nbaPlayerService.getAll();
    }
}