package com.gsir.monitor.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author peter.wang
 * @ClassName ElasticSearchEntity
 * @Description 数据存储对象
 * @date 2020/5/31
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticSearchEntity<T> {
    /**
     * 主键标识，用户ES持久化
     */
    private String id;

    /**
     * JSON对象，实际存储数据
     */
    private Map data;
}
