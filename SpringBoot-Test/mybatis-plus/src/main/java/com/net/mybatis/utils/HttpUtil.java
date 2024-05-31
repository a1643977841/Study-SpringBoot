package com.net.mybatis.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2024/5/31 14:52
 */
public class HttpUtil {

    /**
     * 请求流
     *
     * @param path url
     * @return InputStream
     */
    public static InputStream returnBitMap(String path) {
        InputStream is = null;
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            is = conn.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }

}
