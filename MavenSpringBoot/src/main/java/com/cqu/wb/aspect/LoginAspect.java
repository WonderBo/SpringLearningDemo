/**
 * 
 */
package com.cqu.wb.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author 汪波
 *
 */
@Aspect
@Component
public class LoginAspect {
	
	private final static Logger logger = LoggerFactory.getLogger(LoginAspect.class);
	
	@Pointcut("execution(public * com.cqu.wb.controller.CarController.*(..))")
	public void pointcut() {
		
	}
	
	@Before("pointcut()")
	public void doBefore(JoinPoint joinPoint) {
		logger.info("------------方法之前-----------");
		
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		
		// 获取HTTP请求相关信息
		// URL
		logger.info("URL={}", request.getRequestURI());
		
		// RequestMethod
		logger.info("RequestMethod={}", request.getMethod());
		
		// IP
		logger.info("IP={}", request.getRemoteAddr());
		
		// ClassMethod
		logger.info("ClassMethod={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		
		// RequestArgs
		logger.info("RequestArgs={}", joinPoint.getArgs());
	}
	
	@After("pointcut()")
	public void doAfter() {
		logger.info("------------方法之后-----------");
	}
	
	@AfterReturning(returning = "object", pointcut = "pointcut()")
	public void doAfterReturning(Object object) {
		logger.info("Response={}", object.toString());
	}
}
