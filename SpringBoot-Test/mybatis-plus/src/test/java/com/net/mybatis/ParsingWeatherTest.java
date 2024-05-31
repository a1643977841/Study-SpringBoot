package com.net.mybatis;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2024/5/31 14:37
 */
public class ParsingWeatherTest {

    public static void main(String[] args) {
        String urlStr = "https://aviationweather.gov/data/cache/tafs.cache.xml.gz"; // 请替换为实际的URL
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

                parseJSONFromString(xmlContent.toString());

            } finally {
                connection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void parseJSONFromString(String xmlString) {
        JSONObject jsonObject = JSONUtil.parseFromXml(xmlString);
        if (!jsonObject.containsKey("response")) {
            throw new RuntimeException("FAT JOSN format \"no response\"");
        }
        JSONObject response = jsonObject.getJSONObject("response");
        if (!response.containsKey("data")) {
            throw new RuntimeException("FAT JOSN format \"no data\"");
        }
        JSONObject data = response.getJSONObject("data");
        if (!data.containsKey("TAF")) {
            throw new RuntimeException("FAT JOSN format \"no TAF\"");
        }
        JSONArray tafArr = data.getJSONArray("TAF");
        if (tafArr != null && !tafArr.isEmpty()) {
            for (Object o : tafArr) {
                if (o instanceof JSONObject) {
                    JSONObject taf = (JSONObject) o;
                    if (taf.containsKey("raw_text")) {
                        String rawText = taf.getStr("raw_text");
                        System.out.println(rawText);
                        taf.remove("raw_text");
                    }
                    String tafJsonStr = JSONUtil.toJsonStr(jsonObject);
                }
            }
        }
    }
}
