package com.net.cdc.deserialization;

import cn.hutool.json.JSONUtil;
import com.esotericsoftware.minlog.Log;
import com.net.cdc.domain.DataChangeInfo;
import com.ververica.cdc.debezium.DebeziumDeserializationSchema;
import io.debezium.data.Envelope;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.util.Collector;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2024/3/25 16:25
 */
@Slf4j
public class PgSQLDeserialization implements DebeziumDeserializationSchema<String> {

    public static final String TS_MS = "ts_ms";
    public static final String DATABASE = "db";
    public static final String SCHEMA = "schema";
    public static final String TABLE = "table";
    public static final String BEFORE = "before";
    public static final String AFTER = "after";
    public static final String SOURCE = "source";

    @Override
    public void deserialize(SourceRecord sourceRecord, Collector<String> collector) throws Exception {
        final String topic = sourceRecord.topic();
        log.debug("收到{}的消息，准备进行转换", topic);

        final DataChangeInfo dataChangeInfo = new DataChangeInfo();

        final Struct struct = (Struct) sourceRecord.value();
        final Struct source = struct.getStruct(SOURCE);
        dataChangeInfo.setBeforeData(getDataJsonString(struct, BEFORE));
        dataChangeInfo.setAfterData(getDataJsonString(struct, AFTER));

        //5.获取操作类型  CREATE UPDATE DELETE
        Envelope.Operation operation = Envelope.operationFor(sourceRecord);
        dataChangeInfo.setEventType(operation.toString().toLowerCase());
        dataChangeInfo.setDatabase(Optional.ofNullable(source.get(DATABASE)).map(Object::toString).orElse(""));
        dataChangeInfo.setSchema(Optional.ofNullable(source.get(SCHEMA)).map(Object::toString).orElse(""));
        dataChangeInfo.setTableName(Optional.ofNullable(source.get(TABLE)).map(Object::toString).orElse(""));
        dataChangeInfo.setChangeTime(Optional.ofNullable(struct.get(TS_MS)).map(x -> Long.parseLong(x.toString())).orElseGet(System::currentTimeMillis));


        log.info("收到{}的{}类型的消息， 已经转换好了，准备发往sink", topic, dataChangeInfo.getEventType());
        //7.输出数据
        collector.collect(JSONUtil.toJsonStr(dataChangeInfo));
    }

    private String getDataJsonString(final Struct struct, final String fieldName) {
        if (Objects.isNull(struct)) {
            return null;
        }
        final Struct element = struct.getStruct(fieldName);
        if (Objects.isNull(element)) {
            return null;
        }
        Map<String, Object> dataMap = new HashMap<>();
        Schema schema = element.schema();
        List<Field> fieldList = schema.fields();
        for (Field field : fieldList) {
            dataMap.put(field.name(), element.get(field));
        }
        return JSONUtil.toJsonStr(dataMap);
    }


    @Override
    public TypeInformation<String> getProducedType() {
        return TypeInformation.of(String.class);
    }
}
