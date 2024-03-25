package com.net.cdc.domain;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2024/3/25 16:18
 */
@Data
public class DataChangeInfo {

    /**
     * 变更前数据
     */
    private String beforeData;
    /**
     * 变更后数据
     */
    private String afterData;
    /**
     * 变更类型 create=新增、update=修改、delete=删除、read=初始读
     */
    private String eventType;
    /**
     * 数据库名
     */
    private String database;
    /**
     * schema
     */
    private String schema;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 变更时间
     */
    private Long changeTime;

}
