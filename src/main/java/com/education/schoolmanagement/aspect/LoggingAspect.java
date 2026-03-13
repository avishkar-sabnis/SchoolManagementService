package com.education.schoolmanagement.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Apply pointcut expressions on getAllStudents API
    /*@Pointcut("execution(* com.education.schoolmanagement.service.StudentService.getAllStudents(..))")
    private void pointCutExpression(){
        logger.info("pointcut Expression called");
    }*/


    // Apply pointcut expressions on all API's that exists in com.education.schoolmanagement.service.StudentService package
    /*@Pointcut("within(com.education.schoolmanagement.service.StudentService)")
    private void pointCutExpression(){
        logger.info("pointcut Expression called");
    }*/

    @Around("pointCutExpression()")
    public Object loggingAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Before getAllStudents()");
        Object result = joinPoint.proceed();
        logger.info("After getAllStudents()");
        return result;
    }

    /*@Before("execution(* com.education.schoolmanagement.service.*.*(..))")
    public void beforeLogging(JoinPoint joinPoint) {
        logger.info("Before method: {}", joinPoint.getSignature().getName());
    }*/



}

