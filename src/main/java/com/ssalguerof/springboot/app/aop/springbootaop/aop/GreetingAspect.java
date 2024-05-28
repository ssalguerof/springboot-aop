package com.ssalguerof.springboot.app.aop.springbootaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Order(2)
@Aspect
@Component
public class GreetingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //@Before("execution(String com.ssalguerof.springboot.app.aop.springbootaop.services.GreetingService.sayHello(..))")//Especifica cuando se ejecute un metodo en especifico
    @Before("execution(* com.ssalguerof.springboot.app.aop.springbootaop.services.GreetingService.*(..))")//Se especifica que se ejecute en todos los metodos de una clase
    //@Before("execution(String com.ssalguerof.springboot.app.aop.springbootaop.services.*.*(..))")//Se aplica en todas las clases que se encuentran en el package
    //@Before("execution(* com.ssalguerof.springboot.app.aop.springbootaop..*.*(..))")//Aplicar a todos los sub package del paquete principal
    public void loggerBefore(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Antes: "+ method+ " con los argumentos "+args);
    }

    @After("execution(* com.ssalguerof.springboot.app.aop.springbootaop.services.GreetingService.*(..))")//Aplicar a todos los sub package del paquete principal
    public void loggerAfter(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues: "+ method+ " con los argumentos "+args);
    }

    @AfterReturning("execution(* com.ssalguerof.springboot.app.aop.springbootaop.services.GreetingService.*(..))")//Aplicar a todos los sub package del paquete principal
    public void loggerAfterReturning(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues de retornar: "+ method+ " con los argumentos "+args);
    }

    @AfterThrowing("execution(* com.ssalguerof.springboot.app.aop.springbootaop.services.GreetingService.*(..))")//Aplicar a todos los sub package del paquete principal
    public void loggerAfterThrowing(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues de lanzar la excepcion: "+ method+ " con los argumentos "+args);
    }

    @Around("execution(* com.ssalguerof.springboot.app.aop.springbootaop.services.GreetingService.*(..))")//Aplicar a todos los sub package del paquete principal
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable{
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        Object result = null;

        try {
            logger.info("El método "+ method+ "() con los parametros "+ args);
            result = joinPoint.proceed();
            logger.info("El método "+ method+ "() retorna el resultado: "+ result);
            return result;
        }catch (Throwable e){
            logger.info("Error en la llamada del metodo "+ method+ " () ");
            throw e;
        }

    }
}
