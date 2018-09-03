/**
 * @description 环绕增强(MethodInterceptor)实现类
 */
package com.spring.aop.advice;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

//环绕增强实现的接口是AOP官方提供的接口，而其它增强实现的接口是Spring提供的接口
public class LogAroundAdvice implements MethodInterceptor {
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	/**
	 * @param methodInvocation 该类封装了目标对象，目标方法相关信息
	 */
	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		Object[] args = methodInvocation.getArguments();		//获取目标方法入参数组
		Method method = methodInvocation.getMethod();			//获取目标方法
		Object target = methodInvocation.getThis();				//获取目标对象
		
		logger.log(Level.INFO, "（环绕通知）在删除之前");
		Object retValue = methodInvocation.proceed();			//反射调用目标方法
		logger.log(Level.INFO, "（环绕通知）在删除之后");
		
		System.out.println("目标方法名称：" + method.getName());
		System.out.println("目标方法入参：" + args[0]);
		System.out.println("目标类名称：" + target.getClass().getName());
		
		return retValue;
	}

}
