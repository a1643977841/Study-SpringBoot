package com.net.mybatis.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2024/5/31 16:00
 */
@TableName("weather")
@Data
public class Weather implements Serializable {
    private static final long serialVersionUID = 1L;

    private String icao;

    private String metar;

    private String metarDecode;

    private String updatedTime;

}
