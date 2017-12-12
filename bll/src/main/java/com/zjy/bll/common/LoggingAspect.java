package com.zjy.bll.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by chahongjing on 2017/12/11.
 * 可使用@Order指定切面优先级，值越小，越先执行
 */
@Order(2)
@Component
@Aspect
public class LoggingAspect {
    /**
     * 定义一个方法，用于声明切入点表达式，一般的，该方法不需要添加其它代码
     */
    @Pointcut("execution(* com.zjy.bll.service.*.*(..))")
    public void declareJoinPointExpression() {

    }

    //@Before("execution(* com.zjy.bll.service.*.*(..))")
    @Before("declareJoinPointExpression()")// 如果在类外也可以使用@Before("LoggingAspect.declareJoinPointExpression()")，还可以添加包名
    public void beforeMethod(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("beforeMethod method:" + name + " with agrs:" + args);
    }
    @After("execution(* com.zjy.bll.service.*.*(..))")
    public void afterMethod(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("afterMethod method:" + name + " with agrs:" + args);
    }
    @AfterReturning(value = "execution(* com.zjy.bll.service.*.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String name = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("afterReturning method:" + name + " with agrs:" + args);
    }

    /**
     * 异常发生后执行的代码，第二个参数代表异常类型
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "execution(* com.zjy.bll.service.*.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String name = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("AfterThrowing method:" + name + " with agrs:" + args);
    }

    /**
     * 类似于动态代理全过程，且必须有返回值
     * @param pjd
     */
    @Around("execution(* com.zjy.bll.service.*.*(..))")
    public Object around(ProceedingJoinPoint pjd) {
        Object result = null;

        try {
            String name = pjd.getSignature().getName();
            List<Object> args = Arrays.asList(pjd.getArgs());
            System.out.println("before around method:" + name + " with agrs:" + args);
            result = pjd.proceed();
            System.out.println("return around method:" + name + " with agrs:" + args);
        } catch (Throwable ex) {
            ex.printStackTrace();
            System.out.println("around occurs exception:" + ex);
            throw new RuntimeException(ex);
        }
        System.out.println("after around method:");
        return result;
    }
}
