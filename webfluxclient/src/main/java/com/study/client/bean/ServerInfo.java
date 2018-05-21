package com.study.client.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务器信息类
 * create by Nemo
 * 2018/5/21  23:22
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ServerInfo {

    /**
     * 服务器url
     */
    private String url;
}
