package com.study.client.proxy.impl;

import com.study.client.controller.ApiServer;
import com.study.client.bean.MethodInfo;
import com.study.client.bean.ServerInfo;
import com.study.client.proxy.ProxyCreator;
import com.study.client.proxy.RestHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * create by Nemo
 * 2018/5/21  23:20
 */
@Slf4j
public class ProxyCreatorImpl implements ProxyCreator {
    @Override
    public Object createProxy(Class<?> type) {
        log.info("createProxy:"+type);
        //提取服务器信息
        ServerInfo serverInfo=extractServerInfo(type);
        log.info("serverInfo:"+serverInfo);
        //给每一个代理类一个实现
        RestHandler handler=new WebClient();

        //初始化服务器信息 & 初始化webclient
        handler.init(serverInfo);

        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{type}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        ///根据方法和参数得到调用信息
                        MethodInfo methodInfo=extractMethodInfo(method,args);
                        log.info("methodInfo:"+methodInfo);
                        //调用rest

                        return handler.invokeRest(serverInfo,methodInfo);
                    }
                }
        );
    }

    /**
     * 根据方法定义和调用参数得到调用的相关信息
     * @param method
     * @param args
     * @return
     */
    private MethodInfo extractMethodInfo(Method method, Object[] args) {
        MethodInfo methodInfo = new MethodInfo();
        //得到url 和请求方法
        extractUrlAndMethod(method, methodInfo);

        //得到url 和请求参数和请求体
        extractRequestParamAndBody(method, args, methodInfo);


        return methodInfo;
    }

    /**
     *
     * @param method
     * @param methodInfo
     */
    private void extractUrlAndMethod(Method method, MethodInfo methodInfo) {
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation: annotations) {
            if (annotation instanceof GetMapping){
                GetMapping a=(GetMapping)annotation;
                methodInfo.setUrl(a.value()[0]);
                methodInfo.setMethod(HttpMethod.GET);
            }
            if (annotation instanceof PostMapping){
                PostMapping a=(PostMapping)annotation;
                methodInfo.setUrl(a.value()[0]);
                methodInfo.setMethod(HttpMethod.POST);
            }
            if (annotation instanceof DeleteMapping){
                DeleteMapping a=(DeleteMapping)annotation;
                methodInfo.setUrl(a.value()[0]);
                methodInfo.setMethod(HttpMethod.DELETE);
            }
            if (annotation instanceof PutMapping){
                PutMapping a=(PutMapping)annotation;
                methodInfo.setUrl(a.value()[0]);
                methodInfo.setMethod(HttpMethod.PUT);
            }
        }
    }

    /**
     *
     * @param method
     * @param args
     * @param methodInfo
     */
    private void extractRequestParamAndBody(Method method, Object[] args, MethodInfo methodInfo) {
        //
        Map<String,Object> params=new LinkedHashMap<>();
        //得到body
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //是否带@PathVariable
            PathVariable pathVariable = parameters[i].getAnnotation(PathVariable.class);
            if (pathVariable!=null){
                params.put(pathVariable.value(),args[i]);
            }
            //是否带requestbody
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody!=null){
                methodInfo.setBody((Mono<?>) args[i]);
            }
        }
        methodInfo.setParams(params);
    }

    private ServerInfo extractServerInfo(Class<?> type) {
        ServerInfo serverInfo = new ServerInfo();
        ApiServer annotation = type.getAnnotation(ApiServer.class);
        serverInfo.setUrl(annotation.value());

        return serverInfo;
    }
}
