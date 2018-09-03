/**
 * @description 后置返回通知
 */
package com.spring.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


//(1)用@Aspect注解进行切面声明
@Aspect
public class LogReturnAdvice {
	Logger log = Logger.getLogger(this.getClass().getName());
	
	//(2)声明切入点【@Pointcut+方法】
	@Pointcut(value="execution(* com.spring.test.UserDAO.*(..)) && args(param)", argNames="param")
	public void pointCutMethod(String param){}														//相当于声明切入点阶段(内部方法不执行)
	
	//(3)声明通知
	//(3.4)后置返回通知  @AfterReturning(value="切入点表达式或命名切入点",pointcut="切入点表达式或命名切入点",argNames(可选)="参数列表参数名",returning="返回值对应参数名")
	//作用时间：在切入点选择的方法正常返回时执行
	//value和pointcut任取其一即可，指定了pointcut将覆盖value属性指定的，pointcut具有高优先级；
	//returning限定了只有目标方法返回值类型与通知方法相应参数类型匹配时才能执行后置返回通知，否则不执行。对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
	//returning为拦截目标方法的返回值，returning="retVal"用于将目标返回值赋值给参数名为“retVal”的参数上
	@AfterReturning(value="pointCutMethod(param)",argNames="retVal,param", returning="retVal")		//相当于使用切入点阶段
	public void afterReturningAdvice1(Object retVal, String param){
		log.log(Level.INFO, "后置返回通知（通知切入点分开声明）删除返回之后$$$$\t");
		System.out.println("拦截方法参数值：" + param);
		System.out.println("拦截方法返回值：" + retVal);
	}
	
	
	//可以不用单独声明切入点，即将切入点(匿名切入点)与通知一起声明
//	@AfterReturning(value="execution(* com.spring.test.UserDAO.*(..)) && args(param)",argNames="retVal,param", returning="retVal")
//	public void afterReturningAdvice2(Object retVal, String param){
//		log.log(Level.INFO, "后置返回通知（通知切入点合并声明）删除返回之后$$$$\t");
//		System.out.println("拦截方法参数值：" + param);
//		System.out.println("拦截方法返回值：" + retVal);
//	}
}
