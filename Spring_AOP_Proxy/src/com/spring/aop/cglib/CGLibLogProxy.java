/**
 * @description CGLib代理（为没有实现接口的类提供代理，为JDK的动态代理提供了很好的补充）
 * 				CGLIB代理：实现原理类似于JDK动态代理，只是它在运行期间生成的代理对象是针对目标类扩展的子类
 */
package com.spring.aop.cglib;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

//相同点：CGLib代理与JDK动态代理原理大致是相同的，都是通过绑定目标对象来建立代理对象，然后根据回调机制，当代理对象调用方法时，回调实现的接口的重写方法
//不同点（1）：主要区别在于CGLib通过继承目标类来代理和调用目标类的方法，JDK动态代理通过实现与目标类相同的接口来代理和调用目标类的方法（因此JDK代理中目标对象必须实现接口，而CGLib则不必）
//不同点（2）：CGLib代理无法通知（advice）Final方法，因为它们不能被继承覆写，CGLib代理和JDK动态代理均不能通知/拦截private方法，不仅无法继承，即使继承也无法调用
//不同点（3）：JDK动态代理在创建代理对象时性能高于CGLib，而代理对象的运行性能却比CGLib低，如果是对于singeton对象的代理，推荐使用CGLib代理
//注：CGLib是高效的代码生成包，底层是依靠ASM（开源的java字节码编辑类库）操作字节码实现的，性能比JDK强。
//Spring应用：如果目标对象是实现接口的实例，默认使用JDK动态代理，否则使用CGLib来生成代理
public class CGLibLogProxy implements MethodInterceptor {
	private Object targetObject;	//目标（被代理）对象
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	/**
	 * 
	 * @param targetObject 目标对象
	 * @return 返回创建的代理对象（由于创建的代理对象调用setSuperclass方法继承了目标类，因此只能将其强制转化为对应的目标类类型）
	 * @description 绑定目标对象，返回代理实例
	 */
	public Object bindTargetObject(Object targetObject){
		this.targetObject = targetObject;
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(targetObject.getClass());	//设置继承目标类，表示我要代理的是该目标对象，这样我就能调用目标对象中的方法
		enhancer.setCallback(this);	//设置回调对象，当代理对象调用方法时，就会自动回调该MethodInterceptor实例的intercept方法
		return enhancer.create();	//返回创建的代理对象
	}
	/**
	 * @param proxy
	 * @param method 代理对象被调用的任意方法
	 * @param args 对应method的参数
	 * @param methodProxy 
	 * @return retValue 对应method调用后的返回结果
	 * @description 在调用代理对象的任意方法时，进而（通过MethodInterceptor实例）自动回调该方法被代理后的intercept方法【（可选-拦截）+增强逻辑+业务逻辑】
	 */
	@Override
	public Object intercept(Object proxy, Method method, Object[] args,
			MethodProxy methodProxy) throws Throwable {
		// TODO Auto-generated method stub
		if(method.getName().equals("delete")){	//拦截条件
			logger.log(Level.INFO, "在删除之前");	//增强逻辑
			Object retValue = method.invoke(targetObject, args);	//业务逻辑
			logger.log(Level.INFO, "在删除之后");	//增强逻辑
			
			System.out.println("方法名称： " + method);
			System.out.println("方法参数： " + args[0]);
			System.out.println("方法返回： " + retValue);
			
			return retValue;
		}
		return null;
	}
}
