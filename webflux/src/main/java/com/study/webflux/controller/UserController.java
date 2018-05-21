package com.study.webflux.controller;

import com.study.webflux.repo.entity.User;
import com.study.webflux.repo.entity.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * create by Nemo
 * 2018/5/20  14:04
 */
@RestController
@RequestMapping("/user")
public class UserController {

    UserRepo userRepo;

    public UserController(UserRepo repo) {
        this.userRepo = repo;
    }

    @GetMapping("/")
    public Flux<User>getAll(){
        return userRepo.findAll();
    }

    /**
     * 已sse 形式多次返回
     * @return
     */
    @GetMapping(value = "/stream/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User>getAll2(){
        return userRepo.findAll();
    }
    @PostMapping
    public Mono<User> save(@Valid User user){
        //spring data jpa 里面 新增和修改都是save  有id是修改

        return userRepo.save(user);
    }
    @DeleteMapping("/{id}")
    public  Mono<ResponseEntity<Void>> deleteUser(@PathVariable String id){

        //当你要操作数据，并返回一个Mono 这个时候使用flatmap
        //不操作数据，只是转化数据，使用map
     return    userRepo.findById(id)
                .flatMap(user -> this.userRepo.delete(user)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

    }

    /**
     * 更新
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/{id}")
    public  Mono<ResponseEntity<User>> updateUser( @PathVariable String id,@Valid User user){
      return   userRepo.findById(id)
              // 操作数据
                .flatMap(user1 -> {
                    BeanUtils.copyProperties(user,user1);
                   return userRepo.save(user1);
                })
              //转化数据
                .map(u->new ResponseEntity<User>(HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<User>(HttpStatus.NOT_FOUND));
    }

    /**
     * 查
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public  Mono<ResponseEntity<User>> getUser(@PathVariable String id){
        return   userRepo.findById(id)
                // 操作数据
                //转化数据
                .map(u->new ResponseEntity<User>(HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<User>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/age/{start}/{end}")
    public Flux<User> getUserByAge(@PathVariable("start")int start,@PathVariable("end") int end){
        return userRepo.findByAgeBetween(start,end);
    }

    @GetMapping(value = "/stream/age/{start}/{end}",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> getUserByAgestream(@PathVariable("start")int start,@PathVariable("end") int end){
        return userRepo.findByAgeBetween(start,end);
    }

    /**
     * 自定义查询
     * @param start
     * @param end
     * @return
     */
    @GetMapping(value = "/olduser")
    public Flux<User> olduser(){
        return userRepo.oldUser();
    }



}
