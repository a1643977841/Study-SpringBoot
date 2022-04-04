package con.ah;

import com.ah.RedisApplication;
import com.ah.entity.User;
import com.ah.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/2015:40
 */
@SpringBootTest(classes = RedisApplication.class)
public class RedisApplicationTest {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private RedisUtil redisUtil;
    @Test
    public void redisTest() {
        // opsForValue 操作字符串
        // opsForList 操作List

        // 除了基本的操作 我们常用的方法都可以直接通过redisTemplate操作，比如事务和基本的CRUD

        // 获取redis的连接对象
        // RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        // connection.flushDb();
        // connection.flushAll();
        User user = new User(1, "user","liu5201314.");
        redisTemplate.opsForValue().set("myKey",user);
        Object myKey = redisTemplate.opsForValue().get("myKey");
        System.out.println(myKey);

    }
    @Test
    public void redisTest2() {
        redisUtil.set("name","aHao");
        System.out.println(redisUtil.get("name"));
    }
}
