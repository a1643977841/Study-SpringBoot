package com.net.mybatis.domain.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.net.mybatis.domain.WeatherForecast;
import io.github.linpeilie.annotations.AutoMapper;
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
@Data
@AutoMapper(target = WeatherForecast.class)
public class WeatherForecastVO implements Serializable {
    private final static long serialVersionUID = 1L;

    private String icao;

    private String taf;

    private String tafDecode;

    private String updatedTime;


}
