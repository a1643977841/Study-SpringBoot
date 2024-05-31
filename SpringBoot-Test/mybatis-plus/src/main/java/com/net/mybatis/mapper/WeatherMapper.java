package com.net.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.net.mybatis.domain.Weather;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2024/5/31 16:03
 */
@Mapper
public interface WeatherMapper extends BaseMapper<Weather> {
}
