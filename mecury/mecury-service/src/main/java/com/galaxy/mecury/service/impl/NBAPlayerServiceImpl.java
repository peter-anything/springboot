package com.galaxy.mecury.service.impl;

import com.galaxy.mecury.dao.NBAPlayerMapper;
import com.galaxy.mecury.entity.NBAPlayer;
import com.galaxy.mecury.service.NBAPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
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
