package com.net.xfywzx.service;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2024/6/3 11:56
 */
public abstract class BaseService<T> {

    /**
     * 前置操作
     * @param o 对象
     * @return false ：停止操作
     */
    boolean beforeOperation(T o) {
        System.out.println("前置操作");
        return true;
    }


    /**
     * 后置操作
     * @param o 对象
     */
    void afterOperation(T o) {
        System.out.println("后置操作");
    }
}
