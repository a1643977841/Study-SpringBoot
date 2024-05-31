package com.net.mybatis.service;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2024/5/31 15:55
 */
public interface IWeatherService {

    /**
     * 保存metar报文
     */
    void saveMetar();


    /**
     * 保存taf报文
     */
    void saveTaf();
}
