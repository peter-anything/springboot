package com.galaxy.mecury.api.service;

import com.galaxy.mecury.dao.NBAPlayerMapper;
import com.galaxy.mecury.entity.NBAPlayer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: peter
 * @Date: 2020/6/10 23:49
 * @Description:
 */
public interface NBAPlayerService {
    /**
     * 获取所有用户信息
     * @return
     */
    public List<NBAPlayer> getAll();

    /**
     * 添加用户
     * @param player
     */
    public void save(NBAPlayer player);
}
