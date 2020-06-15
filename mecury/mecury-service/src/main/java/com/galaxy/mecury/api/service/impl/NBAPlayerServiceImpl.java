package com.galaxy.mecury.api.service.impl;

import com.galaxy.mecury.dao.NBAPlayerMapper;
import com.galaxy.mecury.entity.NBAPlayer;
import com.galaxy.mecury.api.service.NBAPlayerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: peter
 * @Date: 2020/6/11 22:13
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
