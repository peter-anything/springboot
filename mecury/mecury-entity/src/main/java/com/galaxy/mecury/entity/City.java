package com.galaxy.mecury.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: peter
 * @Date: 2020/6/17 00:54
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City implements Serializable {
    private static final long serialVersionUID = 1305476368031744968L;
    private String name;
    private String from;
}