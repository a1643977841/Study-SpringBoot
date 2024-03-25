package com.net.cdc.sink;

import lombok.Data;
import org.apache.flink.api.connector.sink2.Sink;
import org.apache.flink.api.connector.sink2.SinkWriter;
import org.springframework.stereotype.Component;

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
@Data
@Component
public class FlinkLogSink implements Sink<String> {



    @Override
    public SinkWriter<String> createWriter(InitContext initContext) throws IOException {
        return new FlinkLogSinkWriter();
    }
}
