/**
 * @description 前置通知(切面支持类:缺少了切入点的切面)
 */
package com.spring.test;

import java.util.logging.Level;
import java.util.logging.Logger;



public class LogBeforeAdvice {
	Logger log = Logger.getLogger(this.getClass().getName());

	//前置通知：在切入点选择的方法之前执行
	public void beforeAdvice(){
		log.log(Level.INFO, "前置通知 删除之前----");
	}
}
