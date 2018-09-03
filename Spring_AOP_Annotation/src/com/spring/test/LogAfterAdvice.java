/**
 * @description 后置最终通知
 */
package com.spring.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

//总结：Spring切面 = 切入点【当为匿名切入点(一次性使用)，切入点将并入到通知】 + 通知            通知 = 连接点【在哪儿】 + 增强逻辑【做什么】 （通知也可称为增强，即Advice）    
//切入点与连接点关系：切入点是相对于切面而言的(横向)，相当于拦截条件；连接点是相对于目标对象而言的(纵向)，相当于目标方法(调用或抛出异常)【Spring只支持方法连接点】。
//				      连接点是通过切入点过滤拦截后定位而得的，即切入点是连接点的前提，或切入点是连接点的集合

//注意事项(1)：argNames元素：注解的argNames元素为可选的，若未手动指定argNames元素对应的值时，程序会自动获取被注解方法的形参列表，并指定给argNames元素。
//			   手动指定的参数列表必须齐全，并且参数顺序不可改变，改变后的参数顺序会导致通知方法形参的赋值结果也会根据顺序发生改变；
//注意事项(2)：通知方法形参数量关系：在拦截目标方法后，获取其各项值(如参数值，返回值，异常)，并且注解通过元素将这些值指定给特定的字段，即产生切入点拦截值，
//			   通知方法的形参必须是一一对应切入点拦截值，从而进行绑定赋值，具体实现例子有后置返回通知的returning元素，后置异常通知的throwing元素，切入点表达式args(param)；
//注意事项(3)：切入点拦截值传递流程：获取的拦截值‘同名传递’给argNames元素对应参数列表中的参数，argNames元素对应参数列表中的参数再‘按顺序(不必同名)传递’给通知方法的各个形参
//			  Spring AOP的Pointcut，After，Around，Before，AfterReturning，AfterThrowing注解都满足此流程
//注意事项(4)：args(param)功能：目标方法参数类型匹配功能(拦截功能)，拦截目标方法参数值后进行绑定(绑定功能)

//注意多个切面(Aspect)的order值与执行顺序的关系，以及切面中各个通知(Advice)的调用顺序
//(1)用@Aspect注解进行切面声明(可以给通过order来指定Aspect执行顺序，实现方式有接口和注解两种方法，order值越小越先执行，通知的执行顺序类似于同心的多个圆)

@Aspect
public class LogAfterAdvice {	
	Logger log = Logger.getLogger(this.getClass().getName());
	
	//(2)声明切入点【@Pointcut+方法】
	/*
	  @Pointcut(value="切入点表达式", argNames(可选)="参数名列表")
	  public void pointCutName(){}
	  
	  value：指定切入点表达式(拦截条件)，"execution(返回值类型  包名.类名.方法名(方法参数))"
	  argNames(可选)：指定命名切入点方法参数列表的参数名字，有多个则用“，”分隔，这些参数将传递给通知方法的形参
	  pointcutName：切入点名字，可以使用该名字进行引用该切入点表达式
	  
	    注：切入点表达式args(param)表示切入点将根据形参类型(非形参名)进行匹配与拦截目标方法，
	           目标方法的参数类型依次为‘在argNames元素对应的参数列表中，同名参数对应顺位的被注解方法的形参’的类型（与上面切入点拦截值传递流程类似，先同名，再同顺位）
	*/
	@Pointcut(value="execution(* com.spring.test.UserDAO.*(..)) && args(param,..)", argNames="param")		//当仅含value = "..."，value =可以省略（注解的约定）
	public void pointCutMethod(String param){}					//相当于声明切入点阶段(内部方法不执行)
	
	//(3)声明通知(前置通知，后置返回通知，后置异常通知，后置最终通知，环绕通知)
	//(3.1)后置最终通知  @After(value="切入点表达式或命名切入点", argNames(可选)="参数列表参数名")
	//作用时间：在切入点选择的方法返回时执行，不管是正常返回还是抛出异常都执行
	@After(value="pointCutMethod(param)", argNames="param")		//相当于使用切入点阶段
	public void afterFinallyAdvice1(String param){
		log.log(Level.INFO, "后置最终通知（通知切入点分开声明）删除之后----");
	}
	
	
	//可以不用单独声明切入点，即将切入点(匿名切入点)与通知一起声明
//	@After("execution(* com.spring.test.UserDAO.*(..)) && args(param)")
//	public void afterFinallyAdvice2(String param){
//		log.log(Level.INFO, "后置最终通知（通知切入点合并声明）删除之后----");
//	}
}
