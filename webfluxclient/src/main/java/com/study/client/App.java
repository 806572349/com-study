package com.study.client;

import com.study.client.controller.IUserApi;
import com.study.client.proxy.ProxyCreator;
import com.study.client.proxy.impl.ProxyCreatorImpl;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * create by Nemo
 * 2018/5/20  13:36
 */
@ControllerAdvice
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

    @Bean
    ProxyCreator jdkProxyCreator(){
        return new ProxyCreatorImpl();
    }
    @Bean
    FactoryBean<IUserApi> userApi(ProxyCreator proxyCreator){
        return new FactoryBean<IUserApi>() {
            /**
             * 返回代理对象
             * @return
             * @throws Exception
             */
            @Override
            public IUserApi getObject() throws Exception {
                return (IUserApi) proxyCreator.createProxy(this.getObjectType());
            }

            @Override
            public Class<?> getObjectType() {
                return IUserApi.class;
            }
        };
    }
}
