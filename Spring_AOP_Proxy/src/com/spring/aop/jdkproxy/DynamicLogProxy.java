/**
 * @description JDK动态代理（由于目标对象为object类，因此可以代理任何类型的目标对象）
 * 				JDK动态代理：其代理对象必须是某个接口的实现，它是通过在运行期间创建一个接口的实现类来完成对目标对象的代理
 */
package com.spring.aop.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DynamicLogProxy implements InvocationHandler {
	
	private Object targetObject;	//目标（被代理）对象
	Logger log = Logger.getLogger(this.getClass().getName());
	
	/**
	 * 
	 * @param targetObject 目标对象
	 * @return 返回创建的代理对象（由于创建的代理对象实现了Class<?>[] interfaces接口，因此只能将其强制转化为对应的interfaces接口类型，而且目标对象也必须实现接口）
	 * @description 绑定目标对象，返回代理实例--》当代理对象调用方法时，就会自动回调InvocationHandler实例的invoke方法（如下）
	 */
	public Object bindTargetObject(Object targetObject) {
		this.targetObject = targetObject;
		
		Class cla = targetObject.getClass();
		//java.lang.reflect.Proxy.newProxyInstance(ClassLoader loader,Class<?>[] interfaces, InvocationHandler h) throws IllegalArgumentException
		//ClassLoader loader：定义了由哪个ClassLoader对象来对创建的代理对象进行加载
		//Class<?>[] interfaces：为代理对象提供的接口，是目标对象所实现的接口，表示我要代理的是该目标对象，这样我就能调用这组接口中的方法
		//InvocationHandler h：传入InvocationHandler实例，当代理对象调用方法时，就会自动回调该InvocationHandler实例的invoke方法
		return Proxy.newProxyInstance(cla.getClassLoader(), cla.getInterfaces(), this);
	}
	
	/**
	 * @param proxy 
	 * @param method 代理对象被调用的任意方法
	 * @param args 对应method的参数
	 * @return retValue 对应method调用后的返回结果
	 * @description 在调用代理对象的任意方法时，进而（通过InvocationHandler实例）自动回调该方法被代理后的invoke方法【（可选-拦截）+增强逻辑+业务逻辑】
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// 主流程可以简述为：拦截目标方法，获取可以应用到此方法上的通知链（Interceptor Chain）,如果有,则应用通知,并执行目标方法;如果没有,则直接反射执行目标方法
		// TODO Auto-generated method stub
		if(method.getName().equals("delete")){	//拦截method后对指定method做增强,其过滤拦截作用相当于AOP中的切点
			log.log(Level.INFO, "在删除之前");	//对拦截后的method方法进行增强（增强逻辑），增强在业务逻辑中执行的位置相当于AOP中的连接点
			Object retValue = method.invoke(targetObject, args);	//目标对象的方法的调用（业务逻辑）
			log.log(Level.INFO, "在删除之后");	//对拦截后的method方法进行增强（增强逻辑）
			
			System.out.println("方法名称： " + method);
			System.out.println("方法参数： " + args[0]);
			System.out.println("方法返回： " + retValue);
			
			return retValue;
		}
		return null;
	}

}
