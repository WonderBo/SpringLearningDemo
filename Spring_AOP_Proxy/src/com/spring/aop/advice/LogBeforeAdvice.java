/**
 * @description 前置增强(MethodBeforeAdvice)实现类
 * 				Spring AOP 只能在方法级别上进行织入增强，Spring提供了4种类型的方法增强，分别是前置增强、后置增强、环绕增强和异常抛出增强
 * 				Spring此外还有一种特殊的引介增强，引介增强是类级别的，它为目标类织入新的接口实现
 */
package com.spring.aop.advice;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.aop.MethodBeforeAdvice;

public class LogBeforeAdvice implements MethodBeforeAdvice {
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	/**
	 * @param method 目标方法
	 * @param args 目标方法入参
	 * @param target 目标对象
	 * @description 前置增强逻辑
	 */
	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		// TODO Auto-generated method stub
		logger.log(Level.INFO, "（前置通知）在删除之前");
		
		System.out.println("目标方法名称：" + method.getName());
		System.out.println("目标方法入参：" + args[0]);
		System.out.println("目标类名称：" + target.getClass().getName());
	}
	
}
