package com.ssalguerof.springboot.app.aop.springbootaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(1)
@Component
@Aspect
public class GreetingFooAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Before("execution(* com.ssalguerof.springboot.app.aop.springbootaop.services.GreetingService.*(..))")//Se especifica que se ejecute en todos los metodos de una clase
    public void loggerBefore(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Antes Primero: "+ method+ " invocados con los parametros "+args);
    }

    @After("execution(* com.ssalguerof.springboot.app.aop.springbootaop.services.GreetingService.*(..))")//Aplicar a todos los sub package del paquete principal
    public void loggerAfter(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues Primero: "+ method+ " invocados con los parametros "+args);
    }
}
