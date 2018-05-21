package com.study.client.proxy.impl;

import com.study.client.bean.MethodInfo;
import com.study.client.bean.ServerInfo;
import com.study.client.proxy.RestHandler;

/**
 * create by Nemo
 * 2018/5/21  23:52
 */
public class WebClient implements RestHandler {
    @Override
    public void init(ServerInfo serverInfo) {

    }

    @Override
    public Object invokeRest(ServerInfo serverInfo, MethodInfo methodInfo) {
        return null;
    }
}
