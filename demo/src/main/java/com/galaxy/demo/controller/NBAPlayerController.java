package com.galaxy.demo.controller;

import com.galaxy.demo.entity.NBAPlayer;
import com.galaxy.demo.service.NBAPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: peter
 * @Date: 2020/6/11 22:02
 * @Description:
 */
@RestController
@RequestMapping("/nbaplayer")
public class NBAPlayerController {
    @Resource
    private NBAPlayerService nbaPlayerService;

    @RequestMapping("/list")
    public List<NBAPlayer> list() {
        return nbaPlayerService.getALL();
    }
}
