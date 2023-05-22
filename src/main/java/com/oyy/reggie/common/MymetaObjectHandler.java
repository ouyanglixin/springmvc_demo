package com.oyy.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 自定义
 */

@Component
@Slf4j
public class MymetaObjectHandler implements MetaObjectHandler {
    @Autowired
    private HttpServletRequest request;

    // 执行insert 语句是触发
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("insertFill 方法被触发");
        log.info("操作者id为:{}",request.getSession().getAttribute("employee"));
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", request.getSession().getAttribute("employee"));
        metaObject.setValue("updateUser", request.getSession().getAttribute("employee"));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("updateFill 法被触发");
        log.info("操作者id为:{}",request.getSession().getAttribute("employee"));
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", request.getSession().getAttribute("employee"));
    }
}
