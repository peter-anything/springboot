package com.gsir.monitor.service.impl;

import com.gsir.monitor.entities.NBAPlayer;
import com.gsir.monitor.mapper.NBAPlayerMapper;
import com.gsir.monitor.service.NBAPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NBAPlayerServiceImpl implements NBAPlayerService {
    @Autowired(required = false)
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
