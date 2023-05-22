package com.oyy.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication
@ServletComponentScan //开启对servlet 组件扫描
public class ReggieApplication {
    public static void main(String[] args) {
        log.info("spring启动类启动成功");
        SpringApplication.run(ReggieApplication.class);
    }
}
