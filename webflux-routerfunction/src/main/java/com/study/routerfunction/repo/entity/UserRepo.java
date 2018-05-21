package com.study.routerfunction.repo.entity;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * create by Nemo
 * 2018/5/20  13:59
 */
@Repository
public interface UserRepo extends ReactiveMongoRepository<User,String> {
    Flux<User> findByAgeBetween(int start, int end);

    @Query("{'age':{'$gte':20,'$lte':30}}")
    Flux<User> oldUser();
}
