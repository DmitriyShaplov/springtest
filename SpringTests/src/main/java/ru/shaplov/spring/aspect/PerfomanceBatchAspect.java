package ru.shaplov.spring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PerfomanceBatchAspect {

    @Around("execution(public * ru.shaplov.spring.service.batch.BatchService+.*(..))")
    public Object perfomanceAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("::BATCH IMPORT::{}\nTOOK: {} millis",
                joinPoint.getSignature().getName(),
                end - start);
        return proceed;
    }
}
