/**
 * @description 前置通知
 */
package com.spring.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//(1)用@Aspect注解进行切面声明
@Aspect
public class LogBeforeAdvice {
	Logger log = Logger.getLogger(this.getClass().getName());
	
	//(2)声明切入点【@Pointcut+方法】
	@Pointcut(value="execution(* com.spring.test.UserDAO.*(..)) && args(param)", argNames="param")
	public void pointCutMethod(String param){}						//相当于声明切入点阶段(内部方法不执行)
	
	//(3)声明通知
	//(3.3)前置通知  @Before(value="切入点表达式或命名切入点", argNames(可选)="参数列表参数名") 
	//作用时间：在切入点选择的方法之前执行
	@Before(value="pointCutMethod(param)", argNames="param")		//相当于使用切入点阶段
	public void beforeAdvice1(String param){
		log.log(Level.INFO, "前置通知（通知切入点分开声明）删除之前----");
	}
	
	
	//可以不用单独声明切入点，即将切入点(匿名切入点)与通知一起声明
//	@Before("execution(* com.spring.test.UserDAO.*(..)) && args(param)")
//	public void beforeAdvice2(String param){
//		log.log(Level.INFO, "前置通知（通知切入点合并声明）删除之前----");
//	}
}
