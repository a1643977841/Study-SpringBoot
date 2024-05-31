package com.net.mybatis.controller;

import com.net.mybatis.service.IWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2024/5/31 16:10
 */
@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final IWeatherService weatherService;

    @GetMapping("/saveTaf")
    public String saveTaf() {
        weatherService.saveTaf();
        return "操作成功!";
    }
}
