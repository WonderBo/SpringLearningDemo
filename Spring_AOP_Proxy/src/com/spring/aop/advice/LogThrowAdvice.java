/**
 * @description 后置异常增强(ThrowsAdvice)实现类
 */
package com.spring.aop.advice;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.aop.ThrowsAdvice;

public class LogThrowAdvice implements ThrowsAdvice {
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	/**
	 * @param method 目标方法
	 * @param args 目标方法入参
	 * @param target 目标对象
	 * @param ex 抛出的异常
	 * @description (1)ThrowsAdvice接口并不定义任何方法，相当于一个标志而已；
	 * 				(2)afterThrowing方法需要自己定义且方法名固定，该方法形参要么有如下四个，要么只有Exception或及子类参数；
	 * 				(3)当提供多个afterThrowing增强方法时，Spring根据目标方法抛出的异常，与afterThrowing方法入参的异常进行自动匹配，选取相似度最高的对应增强
	 */
	public void afterThrowing(Method method, Object[] args, Object target, Exception ex)throws Throwable {
		logger.log(Level.INFO, "（后置异常通知）异常发生");
		
		System.out.println("目标方法名称：" + method.getName());
		System.out.println("目标方法入参：" + args[0]);
		System.out.println("目标类名称：" + target.getClass().getName());
		System.out.println("抛出异常：" + ex.getMessage());
	}
}
