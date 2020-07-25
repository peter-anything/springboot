package com.galaxy.mecury.java.deep.service;

import com.galaxy.mecury.entity.NBAPlayer;

import java.util.List;

public interface NBAPlayerService {
    /**
     * 获取所有用户信息
     *
     * @return
     */
    public List<NBAPlayer> getAll();

    public void insert(NBAPlayer nbaPlayer);
}
