package com.study.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * create by Nemo
 * 2018/5/21  22:59
 */
@RestController
public class TestController {
    //直接注入定义的接口
    @Autowired
    IUserApi userApi;

    @GetMapping("/")
    public  void test(){

        userApi.getAllUser();
        userApi.getUserById("11");
        userApi.deleteUserById("1");
        userApi.createUser(Mono.just(User.builder().age(11).name("11").build()));
        // 直接调用调用 调用rest 接口效果
//        Flux<User> user = userApi.getAllUser();
//        user.subscribe(System.out::print);
    }
}
