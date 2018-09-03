/**
 * @description 后置返回通知(切面支持类:缺少了切入点的切面)
 */
package com.spring.test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogReturnAdvice {
	Logger log = Logger.getLogger(this.getClass().getName());
	
	//后置返回通知：在切入点选择的方法正常返回时执行
	public void afterReturningAdvice(Object retVal, String param){
		log.log(Level.INFO, "后置返回通知 删除返回之后$$$$\t");
		System.out.println("拦截方法参数值：" + param);
		System.out.println("拦截方法返回值：" + retVal);
	}
}
