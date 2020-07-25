package com.gsir.monitor.service;

import com.gsir.monitor.entities.NBAPlayer;

import java.util.List;

public interface NBAPlayerService {
    /**
     * 获取所有用户信息
     *
     * @return
     */
    public List<NBAPlayer> getAll();

    /**
     * 添加用户
     *
     * @param player
     */
    public void save(NBAPlayer player);
}
