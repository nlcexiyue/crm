package com.qiangqiang.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2021/1/7
 * \* Time: 14:03
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */

@Aspect
@Component
public class MyLog {
    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Pointcut(value = "@annotation(com.qiangqiang.annotation.MyLog)")
    public void cutService(){

    }

    @Around(value = "cutService()")
    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("proceedingJoinPoint" + proceedingJoinPoint.toString());
        log.info("proceedingJoinPoint" + proceedingJoinPoint.toString());
        log.info("proceed" + proceed.toString());
        System.out.println("proceed" + proceed.toString());
        return false;
    }

}