package com.galaxy.demo.service;

import com.galaxy.demo.dao.NBAPlayerDao;
import com.galaxy.demo.entity.NBAPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: peter
 * @Date: 2020/6/11 21:54
 * @Description:
 */
@Service
public class NBAPlayerService {
    @Resource
    private NBAPlayerDao nbaPlayerDao;

    public List<NBAPlayer> getALL() {
        return nbaPlayerDao.selectList(null);
    }
}
