package com.net.cdc.listener;

import com.ververica.cdc.debezium.DebeziumSourceFunction;
import lombok.RequiredArgsConstructor;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.connector.sink2.Sink;
import org.apache.flink.runtime.state.hashmap.HashMapStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2024/3/25 16:29
 */
@Component
@RequiredArgsConstructor
public class PgSQLEventListener implements CommandLineRunner {

    private final Sink<String> logSink;
    private final DebeziumSourceFunction<String> pgDataSource;

    @Override
    public void run(final String... args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        env.disableOperatorChaining();
        env.enableCheckpointing(6000L);
        // 配置checkpoint 超时时间
        env.getCheckpointConfig().setCheckpointTimeout(Duration.ofMinutes(60).toMillis());
        //指定 CK 的一致性语义
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
        //设置任务关闭的时候保留最后一次 CK 数据
        env.getCheckpointConfig().setExternalizedCheckpointCleanup(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);

        // 避免扫描快照超时
        env.getCheckpointConfig().setTolerableCheckpointFailureNumber(100);
        env.getCheckpointConfig().setCheckpointInterval(Duration.ofMinutes(10).toMillis());
        // 指定从 CK 自动重启策略
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(Integer.MAX_VALUE, 2000L));
        //设置状态后端
        env.setStateBackend(new HashMapStateBackend());

        DataStreamSource<String> pgDataStream = env.addSource(pgDataSource, "PostgreSQL_source")
                .setParallelism(1);
        // sink到kafka
        pgDataStream.sinkTo(logSink).name("logSink2");

        env.execute("pg_cdc_log");
    }

}
