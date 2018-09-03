/**
 * @description 环绕通知
 */
package com.spring.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

//(1)用@Aspect注解进行切面声明
@Aspect
public class LogAroundAdvice {
	Logger log = Logger.getLogger(this.getClass().getName());
	
	//(2)声明切入点【@Pointcut+方法】
	@Pointcut(value="execution(* com.spring.test.UserDAO.*(..)) && args(param)", argNames="param")
	public void pointCutMethod(String param){}							//相当于声明切入点阶段(内部方法不执行)
	
	//(3)声明通知
	//(3.2)环绕通知  @Around(value="切入点表达式或命名切入点",argNames(可选)="参数列表参数名")
	//作用时间：环绕在切入点选择的连接点处的方法所执行的通知，环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值
	//注意：环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型，在通知实现方法内部使用ProceedingJoinPoint的proceed()方法使目标方法执行(回调)，
	//	   proceed()方法可以传入可选的Object[]数组，该数组的值将被作为目标方法执行时的参数
	@Around(value="pointCutMethod(param)", argNames="pjp,param")		//相当于使用切入点阶段
	public Object aroundAdvice1(ProceedingJoinPoint pjp, String param) throws Throwable {
		log.log(Level.INFO, "环绕通知（通知切入点分开声明）删除之前####");
		Object retValue = pjp.proceed();
		log.log(Level.INFO, "环绕通知（通知切入点分开声明）删除之后####");
		return retValue;
	}
	
	
	//可以不用单独声明切入点，即将切入点(匿名切入点)与通知一起声明
//	@Around(value="execution(* com.spring.test.UserDAO.*(..)) && args(param)", argNames="pjp,param")
//	public Object aroundAdvice2(ProceedingJoinPoint pjp, String param) throws Throwable {
//		log.log(Level.INFO, "环绕通知（通知切入点合并声明）删除之前####");
//		Object retValue = pjp.proceed();
//		log.log(Level.INFO, "环绕通知（通知切入点合并声明）删除之后####");
//		return retValue;
//	}
}
