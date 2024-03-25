package com.net.cdc.source;

import com.net.cdc.deserialization.PgSQLDeserialization;
import com.ververica.cdc.connectors.postgres.PostgreSQLSource;
import com.ververica.cdc.debezium.DebeziumSourceFunction;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.UUID;

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
public class PgSQLDataChangeSource {

    /**
     * 数据库hostname
     */
    private String hostName;

    /**
     * 数据库 端口
     */
    private Integer port;

    /**
     * 库名
     */
    private String database;

    /**
     * 用户名
     */
    @Value("${spring.datasource.username}")
    private String userName;

    /**
     * 密码
     */
    @Value("${spring.datasource.password}")
    private String password;

    /**
     * schema 组
     */
    @Value("${flink-cdc.stream.source.schemas:test_schema}")
    private String[] schemaArray;

    /**
     * 要监听的表
     */
    @Value("${flink-cdc.stream.source.schemas:test_table}")
    private String[] tableArray;

    /**
     * 是否忽略初始化扫描数据
     */
    @Value("${flink-cdc.stream.source.init-read.ignore:false}")
    private Boolean initReadIgnore;

    @Value("${spring.datasource.url}")
    private void splitUrl(String url) {
        final String[] urlSplit = StringUtils.split(url, "/");
        final String[] hostPortSplit = StringUtils.split(urlSplit[1], ":");
        this.hostName = hostPortSplit[0];
        this.port = Integer.parseInt(hostPortSplit[1]);
        this.database = StringUtils.substringBefore(urlSplit[2], "?");
    }

    @Bean("pgDataSource")
    public DebeziumSourceFunction<String> buildPgSqlDataSource() {
        Properties properties = new Properties();
        // 指定连接器启动时执行快照的条件：****重要*****
        //initial- 连接器仅在没有为逻辑服务器名称记录偏移量时才执行快照。
        //always- 连接器每次启动时都会执行快照。
        //never- 连接器从不执行快照。
        //initial_only- 连接器执行初始快照然后停止，不处理任何后续更改。
        //exported- 连接器根据创建复制槽的时间点执行快照。这是一种以无锁方式执行快照的绝佳方式。
        //custom- 连接器根据snapshot.custom.class属性的设置执行快照
        properties.setProperty("debezium.snapshot.mode", "initial");
        properties.setProperty("snapshot.mode", "initial");
        // 好像不起作用使用slot.name
        properties.setProperty("debezium.slot.name", "pg_cdc" + UUID.randomUUID());
        properties.setProperty("slot.name", "flink_slot" + UUID.randomUUID());
        properties.setProperty("debezium.slot.drop.on.top", "true");
        properties.setProperty("slot.drop.on.top", "true");
        // 更多参数配置参考debezium官网 https://debezium.io/documentation/reference/1.2/connectors/postgresql.html?spm=a2c4g.11186623.0.0.4d485fb3rgWieD#postgresql-property-snapshot-mode
        // 或阿里文档 https://help.aliyun.com/document_detail/184861.html

        PgSQLDeserialization deserialization = null;

        if (initReadIgnore) {
            properties.setProperty("debezium.snapshot.mode", "never");
            properties.setProperty("snapshot.mode", "never");
            deserialization = new PgSQLDeserialization();
        } else {
            deserialization = new PgSQLDeserialization();
        }

        return PostgreSQLSource.<String>builder()
                .hostname(hostName)
                .port(port)
                .username(userName)
                .password(password)
                .database(database)
                .schemaList(schemaArray)
                .tableList(tableArray)
                .decodingPluginName("decoderbufs")
                .deserializer(deserialization)
                .debeziumProperties(properties)
                .build();
    }
}
