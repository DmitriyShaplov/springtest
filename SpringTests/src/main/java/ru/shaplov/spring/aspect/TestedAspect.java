package ru.shaplov.spring.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * @author Dmitriy Shaplov
 */
@Aspect
@Component
@Slf4j
public class TestedAspect {

    @Around("execution(* *(javax.servlet.http.HttpSession,..)) && args(httpSession,..))")
    public Object injectSessionAttr(ProceedingJoinPoint joinPoint, HttpSession httpSession) throws Throwable {
        httpSession.setAttribute("aspect test", "Wow, proxied!");
        log.info("before aspect. " + joinPoint.getSignature() + "; " + Arrays.toString(joinPoint.getArgs()) +
                joinPoint.getKind() + "; " + joinPoint.getSourceLocation() + "; " +
                joinPoint.getThis() + "; " + joinPoint.getTarget() + "; " + joinPoint.getStaticPart());

        Object proceed = joinPoint.proceed();

        log.info("after aspect");
        return proceed;
    }

    @Around("target(ru.shaplov.spring.model.IHasPrintVars) && execution(* *())")
    @SneakyThrows
    public Object execute(ProceedingJoinPoint joinPoint) {
        System.out.println("JOINT POINT!!!");
        return joinPoint.proceed();
    }

    @Around("within(ru.shaplov.spring.model.IHasPrintVars) && execution(* *())")
    @SneakyThrows
    public Object execute2(ProceedingJoinPoint joinPoint) {
        System.out.println("JOINT POINT!!!2");
        return joinPoint.proceed();
    }

    @Around("within(ru.shaplov.spring.model.IHasPrintVarsImpl2) && execution(* *())")
    @SneakyThrows
    public Object execute3(ProceedingJoinPoint joinPoint) {
        System.out.println("JOINT POINT!!!3");
        return joinPoint.proceed();
    }

    @Around("execution(* ru.shaplov.spring.model.IHasPrintVars.*())")
    @SneakyThrows
    public Object execute4(ProceedingJoinPoint joinPoint) {
        System.out.println("JOINT POINT!!!4");
        return joinPoint.proceed();
    }

    @Around("execution(* ru.shaplov.spring.model.IHasPrintVarsImpl2.*())")
    @SneakyThrows
    public Object execute5(ProceedingJoinPoint joinPoint) {
        System.out.println("JOINT POINT!!!5");
        return joinPoint.proceed();
    }

    @Before("@annotation(ExecuteAspect) && args(session,..)")
    public void beforeExecute(HttpSession session) {
        log.info("setting session attribute \"aspect\" to \"WOW INJECTED!\"");
        session.setAttribute("aspect", "WOW INJECTED!");
    }

    @After("@annotation(ExecuteAspect) && args(session,..)")
    public void afterExecute(HttpSession session) {
        log.info("gettiong attribute: {}", session.getAttribute("aspect"));
    }
}
