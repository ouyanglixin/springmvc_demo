package com.oyy.reggie.exception;

import com.oyy.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

/**
 * 全局异常处理器
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R<String> ex (Exception ex) {
        ex.printStackTrace();
        if (ex.getMessage().contains("Duplicate entry")) {
            log.info("错误信息为:{}",ex.getMessage());
            String[] s = ex.getMessage().split(":");
            String[] sx =  s[2].split(" ");
            log.info(Arrays.toString(sx));
            String msg = sx[3] + "账号已存在";
            return R.error(msg);
        }
        return R.error("未知错误,操作失败！");
    }
}
