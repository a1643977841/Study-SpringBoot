package com.net.mybatis.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.net.mybatis.domain.WeatherForecast;
import com.net.mybatis.mapper.WeatherForecastMapper;
import com.net.mybatis.mapper.WeatherMapper;
import com.net.mybatis.service.IWeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2024/5/31 15:57
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherServiceImpl implements IWeatherService {

    private final WeatherMapper weatherMapper;

    private final WeatherForecastMapper weatherForecastMapper;

    @Override
    public void saveMetar() {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTaf() {
        String urlStr = "https://aviationweather.gov/data/cache/tafs.cache.xml.gz";
        try {
            // 获取HTTP连接
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 读取响应流并解压
            try (InputStream inputStream = connection.getInputStream();
                 GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
                 InputStreamReader reader = new InputStreamReader(gzipInputStream, StandardCharsets.UTF_8);
                 BufferedReader bufferedReader = new BufferedReader(reader)) {

                // 直接读取并解析
                StringBuilder xmlContent = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    xmlContent.append(line);
                }

                parseJsonFromString(xmlContent.toString());

            } finally {
                connection.disconnect();
            }
        } catch (Exception e) {
            log.error("save TAF Exception： ", e);
        }
    }


    private void parseJsonFromString(String xmlString) {
        JSONObject jsonObject = JSONUtil.parseFromXml(xmlString);
        JSONObject response = jsonObject.getJSONObject("response");
        JSONObject data = response.getJSONObject("data");
        JSONArray tafArr = data.getJSONArray("TAF");
        List<WeatherForecast> weatherForecasts = new ArrayList<>();
        List<String> stationIds = new ArrayList<>();
        if (tafArr != null && !tafArr.isEmpty()) {
            for (Object o : tafArr) {
                if (o instanceof JSONObject) {
                    JSONObject taf = (JSONObject) o;
                    String rawText = taf.getStr("raw_text");
                    taf.remove("raw_text");
                    String stationId = taf.getStr("station_id");
                    String issueTime = taf.getStr("issue_time");
                    String tafJsonStr = JSONUtil.toJsonStr(taf);

                    WeatherForecast weatherForecast = new WeatherForecast();
                    weatherForecast.setIcao(stationId);
                    weatherForecast.setTaf(rawText);
                    weatherForecast.setTafDecode(tafJsonStr);
                    weatherForecast.setUpdatedTime(issueTime);
                    weatherForecasts.add(weatherForecast);
                    stationIds.add(stationId);
                }
            }
        }
        if (!stationIds.isEmpty()) {
            weatherForecastMapper.insertOrUpdateBatch(weatherForecasts);
        }
    }
}
