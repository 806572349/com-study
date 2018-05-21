package com.study.client.proxy;

import com.study.client.bean.MethodInfo;
import com.study.client.bean.ServerInfo;

/**
 * rest 请求调用handler
 * create by Nemo
 * 2018/5/21  23:30
 */
public interface RestHandler {
    /**
     * 初始化服务器信息
     * @param serverInfo
     */
    void init(ServerInfo serverInfo);

    /**
     * 调用rest 请求,返回接口
     * @param serverInfo
     * @param methodInfo
     */
    Object invokeRest(ServerInfo serverInfo, MethodInfo methodInfo);
}
