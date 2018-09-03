/**
 * @description 后置异常通知(切面支持类:缺少了切入点的切面)
 */
package com.spring.test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogThrowAdvice {
	
	Logger log = Logger.getLogger(this.getClass().getName());

	//后置异常通知：在切入点选择的方法抛出异常时执行
	public void afterThrowingAdvice(Throwable ex){
		log.log(Level.INFO, "后置异常通知 异常发生￥￥￥￥");
		System.out.println("目标方法中抛出的异常:"+ex.toString());  
        System.out.println("模拟抛出异常后的增强处理...");  
	}
}
