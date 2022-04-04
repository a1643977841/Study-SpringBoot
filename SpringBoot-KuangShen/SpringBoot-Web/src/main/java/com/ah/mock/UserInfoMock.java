package com.ah.mock;

import com.ah.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/15 22:38
 */
@Repository
public class UserInfoMock {

    public static Map<Integer, User> userInfos;

    static {
        userInfos = new HashMap<Integer, User>();
        userInfos.put(100001,new User(100001,"admin","admin","123456","22"));
        userInfos.put(100002,new User(100002,"ah","ah","123456","21"));
        userInfos.put(100003,new User(100003,"dba","dba","123456","23"));
        userInfos.put(100004,new User(100004,"system","system","123456","24"));
        userInfos.put(100005,new User(100005,"root","root","123456","25"));
    }

}
