package com.study.client.proxy;

/**
 * 创建代理类接口
 * create by Nemo
 * 2018/5/21  23:16
 */
public interface ProxyCreator {
    /**
     * 创建代理类
     * @param tClass
     * @return
     */
    Object createProxy(Class<?> tClass);
}
