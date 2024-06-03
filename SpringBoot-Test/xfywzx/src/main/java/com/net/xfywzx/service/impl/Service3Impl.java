package com.net.xfywzx.service.impl;

import com.net.xfywzx.service.BaseService;
import com.net.xfywzx.service.CommonService;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2024/6/3 12:04
 */
public class Service3Impl extends BaseService<Object> implements CommonService {

    @Override
    public void save() {
        System.out.println("保存数据3");
    }
}
