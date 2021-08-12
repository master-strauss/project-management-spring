package com.jrp.pma.logging;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLoggerAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("within(com.jrp.pma.controllers..*)") //WHERE
//	+ "|| (\"within(com.jrp.pma.dao..*)")
	public void definePackagePointcuts() {
		// empty method to name the location specified in the pointcut
	}

	//@Before, @After, @Around //WHAT
	@Around("definePackagePointcuts()")
	public Object logAround(ProceedingJoinPoint jp) {
		log.debug("\n \n \n");
		log.debug("######## Around Method Execution ######### \n {}.{} () with arguments[s] = {}",
				jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
		log.debug("######## DONE Before ######## \n \n \n");

		Object o = null;
		try {
			o = jp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		log.debug("######## Around Method Execution ######### \n {}.{} () with arguments[s] = {}",
				jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
		log.debug("######## DONE Around ######## \n \n \n");

		return o;

	}
	
	@Before("definePackagePointcuts()")
	public void logBefore(JoinPoint jp) {
		log.debug("\n \n \n");
		log.debug("######## Before Method Execution ######## \n {}.{} {} with arguments[s] = {},"
				+ jp.getSignature().getDeclaringTypeName(),
				jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
		log.debug("--------------------------------------------------------- \n \n \n");
		jp.getSignature().getDeclaringTypeName();
		
	}

	@After("definePackagePointcuts()")
	public void logAfter(JoinPoint jp) {
		log.debug("\n \n \n");
		log.debug("######## After Method Execution ######## \n {}.{} {} with arguments[s] = {},"
				+ jp.getSignature().getDeclaringTypeName(),
				jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
		log.debug("--------------------------------------------------------- \n \n \n");
		jp.getSignature().getDeclaringTypeName();
		
	}

}
