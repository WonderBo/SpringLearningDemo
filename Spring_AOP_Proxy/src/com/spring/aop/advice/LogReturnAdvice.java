/**
 * @description 后置返回增强(AfterReturningAdvice)实现类
 */
package com.spring.aop.advice;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.aop.AfterReturningAdvice;

public class LogReturnAdvice implements AfterReturningAdvice {

	Logger logger = Logger.getLogger(this.getClass().getName());
	
	/**
	 * @param retObj 目标方法返回对象
	 * @param method 目标方法
	 * @param args 目标方法入参
	 * @param target 目标对象
	 */
	@Override
	public void afterReturning(Object retObj, Method method, Object[] args,
			Object target) throws Throwable {
		// TODO Auto-generated method stub
		logger.log(Level.INFO, "（后置返回通知）在删除返回之后");
		
		System.out.println("目标方法返回：" + retObj.toString());
		System.out.println("目标方法名称：" + method.getName());
		System.out.println("目标方法入参：" + args[0]);
		System.out.println("目标类名称：" + target.getClass().getName());
	}

}
