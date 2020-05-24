package com.gsir.monitor.service;

import com.gsir.monitor.entities.User;

import java.util.List;

public interface UserService {

    /**
     * 获取所有用户信息
     * @return
     */
    public List<User> getAll();

    /**
     * 添加用户
     * @param user
     */
    public void save(User user);

}
