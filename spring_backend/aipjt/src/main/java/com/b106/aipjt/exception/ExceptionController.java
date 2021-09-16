package com.b106.aipjt.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.RejectedExecutionException;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({NullPointerException.class, RuntimeException.class, TaskRejectedException.class, Exception.class})
    public ResponseEntity<ErrorResponseDto> handleException(Exception e) {
        e.printStackTrace();
        if (e.getClass()==RuntimeException.class){
            return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto(ErrorCode.RUN_TIME.getCode(), ErrorCode.RUN_TIME.getDescription(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }else if (e.getClass()==NullPointerException.class){
            return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto(ErrorCode.NOT_NULL.getCode(), ErrorCode.NOT_NULL.getDescription(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }else if (e.getClass()==TaskRejectedException.class){
            return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto(ErrorCode.THREAD_POOL.getCode(), ErrorCode.THREAD_POOL.getDescription(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getDescription(), e.getMessage()), HttpStatus.I_AM_A_TEAPOT);
    }
}
