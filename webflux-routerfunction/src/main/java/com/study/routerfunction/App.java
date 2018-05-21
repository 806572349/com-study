package com.study.routerfunction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * create by Nemo
 * 2018/5/20  13:36
 */
@ControllerAdvice
@EnableMongoRepositories
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity handBindException(WebExchangeBindException ex){

        return new ResponseEntity<String>(toStr(ex),HttpStatus.BAD_REQUEST);
    }

    private String toStr(WebExchangeBindException ex) {
    return   ex.getFieldErrors().stream()
                .map(e->e.getField()+":"+e.getDefaultMessage())
                .reduce("",(s1,s2)->s1+"\n"+s2);

    }
}
