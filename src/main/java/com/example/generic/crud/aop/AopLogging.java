package com.example.generic.crud.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class AopLogging {


    @Pointcut("@within(org.springframework.stereotype.Service)")
    private void serviceClasses() {
    }

    @Around("serviceClasses()")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodSignature = joinPoint.getSignature().toShortString();
        try {
            log.debug("Entering method {} with args {}", methodSignature, Arrays.toString(joinPoint.getArgs()));
            long startTime = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - startTime;
            log.debug("Exiting method {} Took {}ms", methodSignature, elapsedTime);
            return result;
        } catch (Exception e) {
            log.error("Error in method {} with args  {}", methodSignature, Arrays.toString(joinPoint.getArgs()), e);
            throw e;
        }
    }
}
