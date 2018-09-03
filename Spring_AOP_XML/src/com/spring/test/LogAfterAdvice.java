/**
 * @description 后置最终通知(切面支持类:缺少了切入点的切面)
 */
package com.spring.test;

import java.util.logging.Level;
import java.util.logging.Logger;



public class LogAfterAdvice {
	Logger log = Logger.getLogger(this.getClass().getName());

	//后置最终通知：在切入点选择的方法返回时执行，不管是正常返回还是抛出异常都执行
	public void afterFinallyAdvice(){
		log.log(Level.INFO, "后置最终通知 删除之后----");
	}
}
