package com.study.routerfunction.router;

import com.study.routerfunction.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.*;

/**
 * create by Nemo
 * 2018/5/20  20:04
 */
@Configuration
public class AllRouters {

    @Bean
    RouterFunction<ServerResponse> userRouter(UserHandler handler){
        return nest(
                // 相当于类上面的 @RequestMapping("/user")
                path("/user"),
                // 下面的相当于类里面的 @RequestMapping
                // 得到所有用户
                route(GET("/"), handler::GetAllUser)
                        // 创建用户
                        .andRoute(POST("/").and(accept(MediaType.APPLICATION_JSON_UTF8)),
                                handler::createUser)
                        // TODO: 2018/5/20 这个有问题
                        // 删除用户
                        .andRoute(DELETE("/{id}"), handler::deleteUser));

    }
}
