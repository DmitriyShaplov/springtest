package ru.shaplov.spring.aspect;

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
