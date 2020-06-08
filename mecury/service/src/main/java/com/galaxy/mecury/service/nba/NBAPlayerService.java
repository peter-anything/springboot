package com.galaxy.mecury.service.nba;

import com.galaxy.mecury.entity.NBAPlayer;

import java.util.List;

/**
 * @Auther: peter
 * @Date: 2020/6/9 01:33
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
