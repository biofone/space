package com.chao.dbmanage.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
public class SysTimeAspect {
    @Pointcut("@annotation(com.chao.dbmanage.common.annotation.RequiredLog)")
    public void doTime() {}
    @Before("doTime()")
    public void doBefore() {
        System.out.println("time doBefore()");
    }
    @After("doTime()")
    public void doAfter() {
        System.out.println("time doAfter()");
    }
    @AfterReturning("doTime()")
    public void doAfterReturning() {
        System.out.println("time doAfterReturning()");
    }
    @AfterThrowing("doTime()")
    public void doAfterThrowing() {
        System.out.println("time doAfterThrowing()");
    }
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("doAround.before");
        Object obj = jp.proceed();
        System.out.println("doAround.after");
        return obj;
    }
}
