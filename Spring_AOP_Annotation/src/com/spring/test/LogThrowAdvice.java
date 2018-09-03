/**
 * @description 后置异常通知
 */
package com.spring.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

//(1)用@Aspect注解进行切面声明
@Aspect
public class LogThrowAdvice {
	Logger log = Logger.getLogger(this.getClass().getName());
	
	//(2)声明切入点【@Pointcut+方法】
	@Pointcut(value="execution(* com.spring.test.UserDAO.*(..)) && args(param)", argNames="param")
	public void pointCutMethod(String param){}												//相当于声明切入点阶段(内部方法不执行)
	
	//(3)声明通知
	//(3.5)后置异常通知  @AfterThrowing(value="切入点表达式或命名切入点",pointcut="切入点表达式或命名切入点",argNames(可选)="参数列表参数名",throwing="异常对应参数名")
	//作用时间：在切入点选择的方法抛出异常时执行
	//value和pointcut任取其一即可，指定了pointcut将覆盖value属性指定的，pointcut具有高优先级；
	//throwing限定了只有目标方法抛出的异常与通知方法相应参数异常类型匹配时才能执行后置异常通知，否则不执行，对于throwing对应的通知方法参数为Throwable类型将匹配任何异常
	//throwing为拦截业务逻辑方法发生的异常，throwing="exception"用于将目标方法抛出的异常赋值给参数名为“exception”的参数上
	@AfterThrowing(value="pointCutMethod(param)",argNames="ex,param", throwing="ex")		//相当于使用切入点阶段
	public void afterThrowingAdvice1(Throwable ex, String param){
		log.log(Level.INFO, "后置异常通知（通知切入点分开声明）异常发生￥￥￥￥");
		System.out.println("目标方法中抛出的异常:" + ex.toString());
		System.out.println("拦截方法参数值：" + param);
        System.out.println("模拟抛出异常后的增强处理..."); 
	}
	
	
	//可以不用单独声明切入点，即将切入点(匿名切入点)与通知一起声明
//	@AfterThrowing(value="execution(* com.spring.test.UserDAO.*(..)) && args(param)", throwing="ex")
//	public void afterThrowingAdvice2(Throwable ex, String param){
//		log.log(Level.INFO, "后置异常通知（通知切入点合并声明）异常发生￥￥￥￥");
//		System.out.println("目标方法中抛出的异常:" + ex.toString());  
//		System.out.println("拦截方法参数值：" + param);
//      System.out.println("模拟抛出异常后的增强处理...");  
//	}
}
