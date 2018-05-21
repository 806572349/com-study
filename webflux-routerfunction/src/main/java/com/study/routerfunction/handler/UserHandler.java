package com.study.routerfunction.handler;

import com.study.routerfunction.repo.entity.User;
import com.study.routerfunction.repo.entity.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * create by Nemo
 * 2018/5/20  19:56
 */
@Slf4j
@Component
public class UserHandler {

    private final UserRepo userRepo;

    public UserHandler(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * 获取全部用户
     * @param request
     * @return
     */
    public Mono<ServerResponse> GetAllUser(ServerRequest request){
        log.info("GetAllUser");
        return ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(this.userRepo.findAll(),User.class);
    }

    /**
     * 创建用户
     * @param request
     * @return
     */
    public Mono<ServerResponse> createUser(ServerRequest request){
        Mono<User> userMono = request.bodyToMono(User.class);
        userMono.filter(u-> {
            System.out.println(u);
            return true;
        });
//        return userMono.flatMap(user -> ok().body(this.userRepo.saveAll(userMono),User.class));
//        return userMono.flatMap(user->{
//           log.info("444");
//           System.out.println(user.toString());
//            return ok().contentType(APPLICATION_JSON_UTF8)
//                    .body(this.userRepo.save(user), User.class);
//
//        });
        return ok().contentType(APPLICATION_JSON_UTF8)
                    .body(this.userRepo.saveAll(userMono), User.class);


    }

    public Mono<ServerResponse> deleteUser(ServerRequest request){
        String id= request.pathVariable("id");

        return userRepo.findById(id).flatMap(user -> {
           return userRepo.delete(user).then(ok().build());
        }).switchIfEmpty(notFound().build());
    }

}
