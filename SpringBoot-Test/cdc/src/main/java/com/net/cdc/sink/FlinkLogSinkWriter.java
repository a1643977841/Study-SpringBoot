package com.net.cdc.sink;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.connector.sink2.SinkWriter;

import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2024/3/25 16:29
 */
@Slf4j
public class FlinkLogSinkWriter implements SinkWriter<String> {
    @Override
    public void write(final String element, final Context context) throws IOException, InterruptedException {
        log.info("收到变更数据：{}", element);
    }

    @Override
    public void flush(boolean b) throws IOException, InterruptedException {

    }

    @Override
    public void close() throws Exception {

    }
}
