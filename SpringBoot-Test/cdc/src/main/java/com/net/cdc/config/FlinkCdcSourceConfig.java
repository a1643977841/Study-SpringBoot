package com.net.cdc.config;


import cn.hutool.core.util.IdUtil;
import com.net.cdc.deserialization.PgSQLDeserialization;
import com.net.cdc.properties.FlinkCdcProperties;
import com.ververica.cdc.connectors.base.source.jdbc.JdbcIncrementalSource;
import com.ververica.cdc.connectors.postgres.PostgreSQLSource;
import com.ververica.cdc.connectors.postgres.source.PostgresSourceBuilder;
import com.ververica.cdc.connectors.postgres.source.config.PostgresSourceConfig;
import com.ververica.cdc.debezium.DebeziumSourceFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;
import java.util.UUID;

@Configuration
public class FlinkCdcSourceConfig {

    @Bean("pgDataSource")
    public JdbcIncrementalSource<String> buildPgSqlDataSource(FlinkCdcProperties prop) {
        Properties properties = new Properties();
        // // 指定连接器启动时执行快照的条件：****重要*****
        // //initial- 连接器仅在没有为逻辑服务器名称记录偏移量时才执行快照。
        // //always- 连接器每次启动时都会执行快照。
        // //never- 连接器从不执行快照。
        // //initial_only- 连接器执行初始快照然后停止，不处理任何后续更改。
        // //exported- 连接器根据创建复制槽的时间点执行快照。这是一种以无锁方式执行快照的绝佳方式。
        // //custom- 连接器根据snapshot.custom.class属性的设置执行快照
        // properties.setProperty("debezium.snapshot.mode", "initial");
        // properties.setProperty("snapshot.mode", "initial");
        // // 好像不起作用使用slot.name
        // properties.setProperty("debezium.slot.name", "pg_cdc" + IdUtil.simpleUUID());
        // properties.setProperty("slot.name", "flink_slot" + IdUtil.simpleUUID());
        // properties.setProperty("debezium.slot.drop.on.top", "true");
        // properties.setProperty("slot.drop.on.top", "true");
        // // 更多参数配置参考debezium官网 https://debezium.io/documentation/reference/1.2/connectors/postgresql.html?spm=a2c4g.11186623.0.0.4d485fb3rgWieD#postgresql-property-snapshot-mode
        // // 或阿里文档 https://help.aliyun.com/document_detail/184861.html
        //
        // PgSQLDeserialization deserialization;
        //
        // if (prop.getIgnore()) {
        //     properties.setProperty("debezium.snapshot.mode", "never");
        //     properties.setProperty("snapshot.mode", "never");
        //     deserialization = new PgSQLDeserialization();
        // } else {
        //     deserialization = new PgSQLDeserialization();
        // }
        return PostgresSourceBuilder.PostgresIncrementalSource.<String>builder()
                .hostname(prop.getHostName())
                .port(prop.getPort())
                .username(prop.getUsername())
                .password(prop.getPassword())
                .database(prop.getDatabase())
                .schemaList(prop.getSchemaArray())
                .tableList(prop.getTableArray())
                .slotName("flink")
                .decodingPluginName("decoderbufs")
                .deserializer(new PgSQLDeserialization())
                .debeziumProperties(properties)
                .includeSchemaChanges(true)
                .splitSize(2)
                .build();
    }
}
