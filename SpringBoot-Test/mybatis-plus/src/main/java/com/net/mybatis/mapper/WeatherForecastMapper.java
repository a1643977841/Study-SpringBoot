package com.net.mybatis.mapper;

import com.net.mybatis.core.BaseMapperPlus;
import com.net.mybatis.domain.WeatherForecast;
import com.net.mybatis.domain.vo.WeatherForecastVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2024/5/31 16:04
 */
@Mapper
public interface WeatherForecastMapper extends BaseMapperPlus<WeatherForecast, WeatherForecastVO> {
}
