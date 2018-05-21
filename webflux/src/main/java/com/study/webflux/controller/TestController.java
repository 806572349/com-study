package com.study.webflux.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Media;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * create by Nemo
 * 2018/5/20  13:37
 */
@RestController
public class TestController {


    @GetMapping("/1")
    public Mono<String> get1() {
        return Mono.fromSupplier(() -> "ress");
    }

    /**
     * 返回流
     * @return
     */
    @GetMapping(value = "/2",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> get2() {
        Flux<String> resutl = Flux.fromStream(IntStream.range(1, 25).mapToObj(x -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "flux data--"+x;
                }
        ));
        return resutl;
    }
}
