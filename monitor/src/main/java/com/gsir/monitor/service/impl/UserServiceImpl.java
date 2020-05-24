package com.gsir.monitor.service.impl;

import com.gsir.monitor.entities.User;
import com.gsir.monitor.mapper.UserMapper;
import com.gsir.monitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public List<User> getAll() {
        return userMapper.selectAll();
    }

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }
}
