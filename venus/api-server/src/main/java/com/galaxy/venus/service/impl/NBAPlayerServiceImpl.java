package com.galaxy.venus.service.impl;

import com.galaxy.venus.dao.NBAPlayerMapper;
import com.galaxy.venus.entity.NBAPlayer;
import com.galaxy.venus.service.NBAPlayerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NBAPlayerServiceImpl implements NBAPlayerService {
    @Resource
    private NBAPlayerMapper nbaPlayerMapper;

    @Override
    public List<NBAPlayer> getAll() {
        return nbaPlayerMapper.selectAll();
    }

    @Override
    public void save(NBAPlayer player) {
        nbaPlayerMapper.insert(player);
    }
}
