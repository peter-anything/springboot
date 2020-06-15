package com.galaxy.mecury.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Auther: peter
 * @Date: 2020/6/15 23:43
 * @Description:
 */
@TableName("msg_log")
@Data
@NoArgsConstructor
public class MsgLog {
    private String msgId;
    private String msg;
    private String exchange;
    private String routingKey;
    private Integer status;
    private Integer tryCount;
    private Date nextTryTime;
    private Date createdTime;
    private Date updatedTime;
}
