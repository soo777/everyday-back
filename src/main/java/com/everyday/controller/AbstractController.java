package com.everyday.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 * 전체 컨트롤러에 공통적인 기능들을 처리
 */
public class AbstractController {

    @Autowired
    private MessageSource messageSource;

    protected String getMessage(String code) {
        return messageSource.getMessage(code, null, null);
    }
}
