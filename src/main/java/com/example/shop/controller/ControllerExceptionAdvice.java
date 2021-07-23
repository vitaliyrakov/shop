package com.example.shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Данный класс перехватывает все исключения, которые были проброшены в процессе выполнения запроса в
 * {@link org.springframework.web.bind.annotation.RestController}
 * Здесь можно настроить их перехват и обработку
 */
@ControllerAdvice
@ResponseBody
public class ControllerExceptionAdvice {

    @ExceptionHandler(IllegalArgumentException.class) //Тип ошибки, который требуется перехватить
    @ResponseStatus(HttpStatus.BAD_REQUEST) //Код ответа
    public String illegalArgumentExceptionHandler(IllegalArgumentException e) {
        return e.getMessage();
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(NullPointerException e) {
        return "Object not found";
    }
}
