/**
 * @description 前置增强测试
 */
package com.spring.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAdvice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//配置文件与代码两种实现增强的方式的主要区别在于代理工厂的设置形式不同
		System.out.println("---通过代码方式实现增强(编程式)---");
		testAdviceByCode();
		
		System.out.println("\n\n---通过配置文件方式实现增强(声明式)---");
		testAdviceByXml();
	}
	
	/**
	 * @description 通过代码（创建ProxyFactory）实现增强
	 */
	private static void testAdviceByCode() {
		/*通过advisedSupport.isOptimize() || advisedSupport.isProxyTargetClass() || hasNoUserSuppliedProxyInterfaces(advisedSupport)条件判断使用JDK动态代理还是CGLIB代理*/
		/*advisedSupport.isOptimize()与advisedSupport.isProxyTargetClass()默认返回都是false，所以在默认情况下目标对象有没有实现接口决定着Spring采取的策略，
		    当然可以设置advisedSupport.isOptimize()或者advisedSupport.isProxyTargetClass()返回为true，这样无论目标对象有没有实现接口Spring都会选择使用CGLIB代理*/
		
		UserDAO target = new UserDAOImpl();										//目标对象--纵向
		MethodBeforeAdvice beforeAdvice = new LogBeforeAdvice();				//增强（通知）--横向
		MethodInterceptor aroundAdvice = new LogAroundAdvice();
		AfterReturningAdvice afterReturningAdvice = new LogReturnAdvice();
		ThrowsAdvice throwsAdvice = new LogThrowAdvice();
		
		ProxyFactory proxyFactory = new ProxyFactory();						//Spring代理工厂（内部使用JDK动态代理与CGLib代理将增强运用到目标类中）
		proxyFactory.setInterfaces(target.getClass().getInterfaces());		//指定针对接口进行代理，ProxyFactory就是用JdkDynamicAopProxy(如果是针对类型的代理，则使用Cglib2AopProxy)
		proxyFactory.setOptimize(true);										//ProxyFactory启动优化代理方式，此时针对接口的代理也会使用Cglib2AopProxy
		proxyFactory.setTarget(target);										//设置代理目标
		proxyFactory.addAdvice(beforeAdvice);								//为代理目标添加增强（可以添加多个增强形成增强链，前置增强正序调用，后置增强逆序调用，环绕增强前正、后逆）
		proxyFactory.addAdvice(afterReturningAdvice);						//addAdvice方法实际为addAdvice(int pos, Advice advice), pos表示增强的index，默认从零开始递增
		proxyFactory.addAdvice(aroundAdvice);
		proxyFactory.addAdvice(throwsAdvice);
		
		UserDAO dao = (UserDAO) proxyFactory.getProxy();					//生成代理实例
		dao.delete("mary");													//只能调用对应接口的方法
	}
	
	/**
	 * @description 通过XML配置文件实现增强
	 */
	private static void testAdviceByXml() {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/spring/aop/advice/conf-advice.xml");
		
		UserDAO dao = applicationContext.getBean("userDao", UserDAO.class);
		dao.delete("kangkang");
	}
}
