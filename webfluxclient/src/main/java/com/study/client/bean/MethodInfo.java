package com.study.client.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 方法调用信息类
 * create by Nemo
 * 2018/5/21  23:25
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MethodInfo {

    /**
     * 请求url
     */
    private String url;
    /**
     * 请求方法
     */
    private HttpMethod method;

    /**
     * 参数（url）
     */
    private Map<String,Object> params;

    /**
     * 请求体
     */
    private Mono<?> body;




}
