package com.example.demo.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AsyncService {
	@Async
	public CompletableFuture run() {
		return new AsyncResult(helloWolrd()).completable();
	}
	
	@Async("asyncThread")
    public ListenableFuture<Integer> listenableFuture(int i) throws InterruptedException {
        Thread.sleep(1000);
        return new AsyncResult<>(i);
    }

    @Async("asyncThread")
    public CompletableFuture<String> completableFuture(int i) throws InterruptedException {
        /*CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()-> hello(),executor);
        completableFuture.thenApplyAsync(s -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("completable : {}",s);
            return "completable";
        },executor);*/

        return new AsyncResult<String>(helloWolrd()).completable();
    }
	
	public String helloWolrd() {
		try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		return "Hello World !";
	}
}
