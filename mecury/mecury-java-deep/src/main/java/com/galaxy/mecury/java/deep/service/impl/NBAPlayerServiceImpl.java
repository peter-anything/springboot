package com.galaxy.mecury.java.deep.service.impl;

import com.galaxy.mecury.entity.NBAPlayer;
import com.galaxy.mecury.java.deep.dao.NBAPlayerMapper;
import com.galaxy.mecury.java.deep.service.NBAPlayerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(NBAPlayer nbaPlayer) {
        nbaPlayerMapper.insertSelective(nbaPlayer);
        int i = 1 / 0;
    }
}