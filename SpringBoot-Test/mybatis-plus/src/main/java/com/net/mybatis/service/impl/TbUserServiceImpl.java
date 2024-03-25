package com.net.mybatis.service.impl;

import com.net.mybatis.domain.TbUser;
import com.net.mybatis.mapper.TbUserMapper;
import com.net.mybatis.service.ITbUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2023/11/10 23:47
 */
@Service
@RequiredArgsConstructor
public class TbUserServiceImpl implements ITbUserService {

    private final TbUserMapper tbUserMapper;

    @Override
    public void save() {
        try {
            byte[] bytes = readFileByBytes("/Users/ahao/Downloads/头像.png");
            TbUser tbUser = new TbUser();
            tbUser.setTestBlob(bytes);
            tbUser.setUsername("阿浩啊");;
            tbUser.setAddress("重庆市是去");
            tbUserMapper.insert(tbUser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateById(Long id) {
        try {
            TbUser tbUser = new TbUser();
            tbUser.setId(id);
            tbUser.setUsername("liuhaos");
            tbUser.setTestBlob(readFileByBytes("/Users/ahao/Downloads/背面.jpeg"));
            tbUserMapper.updateById(tbUser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * 以byte[]方式读取文件
     *
     * @param fileName 文件名
     * @return byte[]
     * @throws IOException io异常
     */
    public static byte[] readFileByBytes(String fileName) throws IOException {
        try (InputStream in = new BufferedInputStream(Files.newInputStream(Paths.get(fileName)));
             ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            byte[] tempBytes = new byte[in.available()];
            for (int i = 0; (i = in.read(tempBytes)) != -1;) {
                out.write(tempBytes, 0, i);
            }
            return out.toByteArray();
        }
    }
}
