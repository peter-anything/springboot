package com.galaxy.venus.dao;

import com.galaxy.venus.entity.NBAPlayer;

import java.util.List;

public interface NBAPlayerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NBAPlayer record);

    int insertSelective(NBAPlayer record);

    NBAPlayer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NBAPlayer record);

    int updateByPrimaryKey(NBAPlayer record);

    List<NBAPlayer> selectAll();
}