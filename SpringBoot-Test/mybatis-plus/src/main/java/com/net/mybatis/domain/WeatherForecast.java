package com.net.mybatis.domain;

import com.baomidou.mybatisplus.annotation.TableId;
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
 * @date 2024/5/31 15:59
 */
@TableName("weather_forecast")
@Data
public class WeatherForecast implements Serializable {
    private final static long serialVersionUID = 1L;

    @TableId
    private String icao;

    private String taf;

    private String tafDecode;

    private String updatedTime;


}
