package com.zjy.bll.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by chahongjing on 2017/12/11.
 */
@Component
@Aspect
public class LoggingAspect {
    @Before("execution(* com.zjy.bll.service.*.*(int, int))")
    public void beforeMethod(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("before method:" + name + " with agrs:" + args);
    }
}
