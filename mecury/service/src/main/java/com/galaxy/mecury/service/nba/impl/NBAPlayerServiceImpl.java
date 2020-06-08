package com.galaxy.mecury.service.nba.impl;

import com.galaxy.mecury.dao.NBAPlayerMapper;
import com.galaxy.mecury.entity.NBAPlayer;
import com.galaxy.mecury.service.nba.NBAPlayerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: peter
 * @Date: 2020/6/9 01:34
 * @Description:
 */
@Service
public class NBAPlayerServiceImpl implements NBAPlayerService {
    @Resource
    private NBAPlayerMapper nbaPlayerMapper;

    @Override
    public List<NBAPlayer> getAll() {
        return nbaPlayerMapper.selectList(null);
    }

    @Override
    public void save(NBAPlayer player) {
        nbaPlayerMapper.insert(player);
    }
}

