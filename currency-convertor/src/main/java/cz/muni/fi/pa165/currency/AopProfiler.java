/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.currency;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 *
 * @author xmedved4
 */
@Aspect
public class AopProfiler {

    @Pointcut("execution(* cz.muni.fi.pa165.currency.*.*(..))")
    public void businessMethods() {
    }

    @Around("businessMethods()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
//        System.out.println("Going to call the method." + pjp.toString());
        Object output = pjp.proceed();
//        System.out.println("Method execution completed.");
        long elapsedTime = System.nanoTime() - start;
        System.out.println("Method " +pjp.getSignature().getName()+ " execution time: " + elapsedTime + " nanoseconds.");
        return output;
    }

}
