package com.galaxy.mecury.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.galaxy.mecury.api.service.MsgLogService;
import com.galaxy.mecury.dao.MsgLogMapper;
import com.galaxy.mecury.entity.MsgLog;
import org.springframework.stereotype.Service;

/**
 * @Auther: peter
 * @Date: 2020/6/15 23:50
 * @Description:
 */
@Service
public class MsgLogServiceImpl extends ServiceImpl<MsgLogMapper, MsgLog> implements MsgLogService {
}
