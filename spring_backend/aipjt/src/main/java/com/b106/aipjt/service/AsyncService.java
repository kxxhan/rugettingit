package com.b106.aipjt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncService {

    @Async
    public void execute() throws InterruptedException {
        Thread.sleep(5000);
    }
}
