/**
 * @description 静态/动态代理测试
 */
package com.spring.aop.jdkproxy;

public class Test {
	public static void main(String[] args) {
		//未使用代理
		System.out.println("-----未使用代理-----");
		UserDAO dao1 = new UserDAOImpl();
		dao1.delete("kangkang");
		
		//静态代理
		System.out.println("\n-------静态代理-------");
		UserDAO dao2 = new LogProxy(new UserDAOImpl());
		dao2.delete("kangkang");
		
		//动态代理
		//注意：目标对象必须实现接口，且由bindTargetObject方法创建的代理对象只能转化为目标对象实现的接口类型（不能转化为目标对象类或者其它类型的接口）
		System.out.println("\n-------动态代理-------");
		Class<?> clazz = new DynamicLogProxy().bindTargetObject(new UserDAOImpl()).getClass();
		for(int i=0; i<clazz.getInterfaces().length; i++){
			System.out.println("代理对象的接口：" + clazz.getInterfaces()[i].getName());
		}
		System.out.println("代理对象的类型：" + clazz.getName());
		
		UserDAO dao3 = (UserDAO) new DynamicLogProxy().bindTargetObject(new UserDAOImpl());
		dao3.delete("kangkang");
	}
}
