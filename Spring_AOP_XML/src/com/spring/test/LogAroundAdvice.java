/**
 * @description 环绕通知(切面支持类:缺少了切入点的切面)
 */
package com.spring.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAroundAdvice {
	Logger log = Logger.getLogger(this.getClass().getName());
	
	//环绕通知：环绕着在切入点选择的连接点处的方法所执行的通知，环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值
	//注意：环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型，在通知实现方法内部使用ProceedingJoinPoint的proceed()方法使目标方法执行(回调)，
	//	   proceed()方法可以传入可选的Object[]数组，该数组的值将被作为目标方法执行时的参数
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		log.log(Level.INFO, "环绕通知 删除之前####");
		Object retValue = pjp.proceed();
		log.log(Level.INFO, "环绕通知 删除之后####");
		return retValue;
	}
}
