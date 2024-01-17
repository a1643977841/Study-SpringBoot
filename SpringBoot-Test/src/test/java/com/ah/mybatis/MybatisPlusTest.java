package com.ah.mybatis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2024/1/17 10:22
 */
@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test() {
        jdbcTemplate.execute("select test_blob, test_bytea from test.test_table1");
        System.out.println("test");
    }
}
