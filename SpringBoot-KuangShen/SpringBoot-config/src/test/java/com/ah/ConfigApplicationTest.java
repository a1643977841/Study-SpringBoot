package com.ah;

import com.ah.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/13 9:31 PM
 */
@SpringBootTest
public class ConfigApplicationTest {
    @Autowired
    private Person person;
    @Test
    public void toPerson() {
        System.out.println(person);
    }
}
